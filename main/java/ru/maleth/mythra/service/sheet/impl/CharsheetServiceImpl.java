package ru.maleth.mythra.service.sheet.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.AbilityChargeModifierDto;
import ru.maleth.mythra.dto.AbilityJsonResponseDto;
import ru.maleth.mythra.dto.AbilityJsonResponseMapper;
import ru.maleth.mythra.dto.NumberModifierDto;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.enums.ProfEnum;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.Proficiency;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.repo.CharRaceAbilityRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.repo.ProficiencyRepo;
import ru.maleth.mythra.utility.CharacterCalculator;
import ru.maleth.mythra.service.sheet.CharsheetService;
import ru.maleth.mythra.utility.classes.ClassUtils;
import ru.maleth.mythra.utility.races.RaceUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharsheetServiceImpl implements CharsheetService {

    private final CharacterRepo characterRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;
    private final CharRaceAbilityRepo charRaceAbilityRepo;
    private final ClassUtils classUtils;
    private final RaceUtils raceUtils;

    private final Gson gson;
    private static final String PAGE = "directToPage";

    @Override
    public Map<String, String> getSheet(Character character) {
        log.info("Собираем модель персонажа " + character.getCharName() + " для вывода на чаршит!");

        /*
        Создаем мапу, чтобы собирать в нее атрибуты, которые будут выводиться на странице с листо персонажа.
         */
        Map<String, String> attributes = new HashMap<>();

        /*
        Создаем ключевые переменные, чтобы формировать атрибуты
         */
        int strength = character.getStrength();
        int dexterity = character.getDexterity();
        int constitution = character.getConstitution();
        int intelligence = character.getIntelligence();
        int wisdom = character.getWisdom();
        int charisma = character.getCharisma();
        int experience = character.getExperience();
        int curHitPoints = character.getCurrentHP();
        int maxHitPoints = character.getMaxHP();

        /*
        Создаем Set, и засовываем сюда названия всех профишиенси персонажа
         */
        Set<String> characterProficiencies = character.getProficiencies().stream().map(p
                -> p.getName().toUpperCase().replace('-', '_')).collect(Collectors.toSet());

        /*
        Собираем значения навыков и профишиенси, которые будем передавать в мапу
        allProficienciesList – список всех имеющихся профишиенси (специализаций в навыках) в формате SLEIGHT_OF_HAND.
        Чтобы сравнить со специализациями в списке characterProficiencies, содержимое characterProficiencies нужно
        перевести в формат NN_NN.
        Когда мы сравниваем, смотрим, присутствует ли специализация в списке специализаций персонажа – т.е.
        находим, в каких специализациях из всех профишиент наш персонаж. Если совпал, выцепляем эту специализацию
        из персонажа, чтобы увеличить показатель.
         */
        List<String> allProficienciesList = Arrays.stream(ProfEnum.values()).map(Enum::toString).toList();
        for (String s : allProficienciesList) {
            if (characterProficiencies.contains(s)) {
                /*
                Выцепив одну (например SLEIGHT_OF_HAND), берем ее атрибут (в нашем случае Ловкость).
                И засовываем в мапу. Имя атрибута – имя профы в нижнем регистре (т.е. sleight_of_hand), значение –
                модификатор Ловкости + бонус мастерства.
                 */
                Proficiency proficiency = character.getProficiencies().stream().filter(p
                        -> p.getName().equals(s)).findFirst().get();
                AttribEnum baseAttrib = AttribEnum.valueOf(proficiency.getBaseAttribute().toString());
                switch (baseAttrib) {
                    case STRENGTH -> attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(strength)
                                    + CharacterCalculator.getProfBonus(character.getExperience())));
                    case DEXTERITY -> attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)
                                    + CharacterCalculator.getProfBonus(character.getExperience())));
                    case INTELLIGENCE ->
                            attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)
                                            + CharacterCalculator.getProfBonus(character.getExperience())));
                    case WISDOM -> attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)
                                    + CharacterCalculator.getProfBonus(character.getExperience())));
                    case CHARISMA -> attributes.put(ProfEnum.valueOf(proficiency.getName()).toString().toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(charisma)
                                    + CharacterCalculator.getProfBonus(character.getExperience())));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            } else {
                /*
                Если навык из общего списка отсутствует в специализациях персонажа, ему присваивается базовое значение.
                И засовывается в мапу. Формат навыка, который отправляется в мапу в итоге получается sleight_of_hand.
                 */
                switch (s) {
                    case "ATHLETICS" -> attributes.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
                    case "ACROBATICS", "STEALTH", "SLEIGHT_OF_HAND" -> attributes.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
                    case "ARCANA", "HISTORY", "INVESTIGATION", "NATURE", "RELIGION" -> attributes.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
                    case "INSIGHT", "MEDICINE", "PERCEPTION", "SURVIVAL", "ANIMAL_HANDLING" ->
                            attributes.put(s.toLowerCase(),
                                    formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
                    case "DECEPTION", "INTIMIDATION", "PERFORMANCE", "PERSUASION" -> attributes.put(s.toLowerCase(),
                            formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            }
        }

        /*
        На странице нужно вывести еще и спасброски. В двух из них персонаж специализируется.
        Специализацию дает класс – находим класс персонажа и выцепляем из него две переменные спасбросков, а затем
        добавляем в список спасбросков.
        */
        List<AttribEnum> savingThrows = new ArrayList<>();
        savingThrows.add(character.getMainClass().getSavingThrowOne());
        savingThrows.add(character.getMainClass().getSavingThrowTwo());

        List<AttribEnum> allSavingThrows = Arrays.stream(AttribEnum.values()).toList();

        for (AttribEnum s : allSavingThrows) {
            if (savingThrows.contains(s)) {
                AttribEnum baseAttrib = AttribEnum.getAttribByName(s.getName());
                switch (baseAttrib.toString()) {
                    case "STRENGTH" -> attributes.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(strength) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "DEXTERITY" -> attributes.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(dexterity) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "CONSTITUTION" -> attributes.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(constitution) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "INTELLIGENCE" -> attributes.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(intelligence) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "WISDOM" -> attributes.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(wisdom) + CharacterCalculator.getProfBonus(character.getExperience())));
                    case "CHARISMA" -> attributes.put(s.toString().toLowerCase() + "save",
                            formatMods(CharacterCalculator.calculateAttributeModifier(charisma) + CharacterCalculator.getProfBonus(character.getExperience())));
                    default -> throw new RuntimeException("Не получилось проставить спасброски");
                }
            } else {
                /*
                Если навык из общего списка отсутствует в специализациях персонажа, ему присваивается базовое значение.
                И засовывается в мапу. Формат навыка, который отправляется в мапу в итоге получается sleight_of_hand.
                 */
                switch (s.toString()) {
                    case "STRENGTH" -> attributes.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
                    case "DEXTERITY" -> attributes.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
                    case "CONSTITUTION" -> attributes.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(constitution)));
                    case "INTELLIGENCE" -> attributes.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
                    case "WISDOM" -> attributes.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
                    case "CHARISMA" -> attributes.put(s.toString().toLowerCase() + "save", formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));
                    default -> throw new RuntimeException("Не получилось проставить спасброски");
                }
            }
        }

        /*
        В зависимости от класса и уровня этого класса, у персонажа есть умения (абилки).
        Зачастую кол-во раз использования умений зависит от персонажа.
        Тут мы формируем список умений (по сути одно умение на класс) и сохраняем его в двух репозиториях.
        В AbilityRepo и в CharClassAbilityRepo (cca).
        Второй репо нужен, чтобы у нас не было миллиона одинаковых Ability, разница в которых исключительно в кол-ве
        применений. Плюс, полагаю, в дальнейшем именно этим репо буду пользоваться, чтобы обновлять кол-во использований.
        */
        //log.info("Собираем абилки " + character.getCharName() + " на основе возможностей классов!");
        /*!!!ВОЗМОЖНО ТУТ НЕ НУЖНЫ ВСЕ АБИЛКИ, А ТОЛЬКО ТЕ, КОТОРЫЕ ВЛИЯЮТ НА ${values} НА ЧАРШИТЕ!!!*/
        //classUtils.charClassAbilityFormer(character);
        //log.info("Собираем абилки " + character.getCharName() + " на основе возможнтей расы!");
        //raceUtils.charRaceAbilityFormer(character);

        /*
        Из интересного – сперва я передавал все абилки в атрибуты и там выводил, а потом научился динамически формировать
        список в SheetController – поэтому эти вот два списка сверху спорно актуальны.
         */

        /*
        Тут переносим атрибуты в мапу. И указываем адрес страницы.
        */
        attributes.put("charName", character.getCharName());
        attributes.put("charRace", character.getCharRace().getRaceEnum().getName());
        attributes.put("charClass", ClassEnum.valueOf(character.getMainClass().getName()).getName());
        attributes.put("ac", String.valueOf(character.getArmorClass()));
        attributes.put("speed", String.valueOf(character.getCharRace().getSpeed()));
        attributes.put("initiative", formatMods(character.getInitiative()));
        attributes.put("experience", String.valueOf(experience));
        attributes.put("level", String.valueOf(CharacterCalculator.getLevel(experience)));
        attributes.put("proficiency", formatMods(CharacterCalculator.getProfBonus(experience)));
        attributes.put("strength", String.valueOf(strength));
        attributes.put("strengthmod", formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
        attributes.put("dexterity", String.valueOf(dexterity));
        attributes.put("dexteritymod", formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
        attributes.put("constitution", String.valueOf(constitution));
        attributes.put("constitutionmod", formatMods(CharacterCalculator.calculateAttributeModifier(constitution)));
        attributes.put("intelligence", String.valueOf(intelligence));
        attributes.put("intelligencemod", formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
        attributes.put("wisdom", String.valueOf(wisdom));
        attributes.put("wisdommod", formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
        attributes.put("charisma", String.valueOf(charisma));
        attributes.put("charismamod", formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));
        attributes.put("curHitPoints", String.valueOf(curHitPoints));
        attributes.put("maxHitPoints", String.valueOf(maxHitPoints));

        if (character.getCharRace().isHasDarkvision()) {
            attributes.put("darkvision", "Да");
        } else {
            attributes.put("darkvision", "Нет");
        }

        attributes.put("charId", String.valueOf(character.getId()));
        attributes.put(PAGE, "charsheet");
        log.info("Отправляем персонажа " + character.getCharName() + " на фронт!");
        return attributes;
    }

    private String formatMods(int mod) {
        if (mod > 0) {
            return ("+" + mod);
        }
        return String.valueOf(mod);
    }

    @Override
    public String abilityLoader(Long charId) {
        log.info("Собираем список абилок для вывода на чаршите персонажа с id {}", charId);
        Character character = characterRepo.findById(charId).get();
        /*
        List<AbilityJsonResponseDto> ccaList = charClassAbilityRepo.findAllByCharacter_IdOrderByAbilityAsc(charId)
                .stream().map(AbilityJsonResponseMapper::fromCharClassAbility).toList();
        List<AbilityJsonResponseDto> craList = charRaceAbilityRepo.findAllByCharacter_IdAndRaceLimitByLevelOrderByAbility_Name(character, character.getCharRace(), CharacterCalculator.getLevel(character.getExperience()))
                .stream().map(AbilityJsonResponseMapper::fromCharRaceAbility).toList();
         */
        List<AbilityJsonResponseDto> ccaList = classUtils.charClassAbilityFormer(character)
                .stream().map(AbilityJsonResponseMapper::fromCharClassAbility).toList();
        List<AbilityJsonResponseDto> craList = raceUtils.charRaceAbilityFormer(character)
                .stream().map(AbilityJsonResponseMapper::fromCharRaceAbility).toList();
        List<AbilityJsonResponseDto> abilList = new ArrayList<>();
        abilList.addAll(ccaList);
        abilList.addAll(craList);
        for (AbilityJsonResponseDto a : abilList) {
            log.info("Добавлена абилка: " + a.getName());
        }
        String response = gson.toJson(abilList);
        log.info("Cписок абилок для вывода на чаршите персонажа с id {} собран в стрингу Json", charId);
        return response;
    }

    @Override
    public String updAbilityCharge(AbilityChargeModifierDto abilityChargeModifierDto) {
        if (abilityChargeModifierDto.getIsFromClass()) {
            CharClassAbility cca = charClassAbilityRepo.findByCharacter_IdAndAbility_Name(abilityChargeModifierDto.getCharId(), abilityChargeModifierDto.getAbilName());
            cca.setNumberOfUses(abilityChargeModifierDto.getModifier());
            charClassAbilityRepo.updateCharges(cca.getAbility().getName(), cca.getCharacter().getCharName(), cca.getCharClass().getName(), cca.getNumberOfUses());
            String response = gson.toJson(cca);
            return response;
        } else {
            CharRaceAbility cra = charRaceAbilityRepo.findByCharacter_IdAndAbility_Name(abilityChargeModifierDto.getCharId(), abilityChargeModifierDto.getAbilName());
            cra.setNumberOfUses(abilityChargeModifierDto.getModifier());
            charRaceAbilityRepo.updateCharges(cra.getAbility().getName(), cra.getCharacter().getCharName(), cra.getRace(), cra.getNumberOfUses());
            String response = gson.toJson(cra);
            return response;
        }
    }

    @Override
    public String updExp(NumberModifierDto numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        if (CharacterCalculator.getLevel(character.getExperience() + numberModifierDto.getModifier()) > CharacterCalculator.getLevel(character.getExperience())) {
            character.setIsLevelUpReady(true);
        }
        character.setExperience(character.getExperience() + numberModifierDto.getModifier());
        String response = gson.toJson(character);
        characterRepo.updateExp(character.getCharName(), character.getExperience());
        return response;
    }

    @Override
    public String updHeal(NumberModifierDto numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        character.setCurrentHP(character.getCurrentHP() + numberModifierDto.getModifier());
        characterRepo.updateHP(character.getCharName(), character.getCurrentHP());
        String response = gson.toJson(character);
        return response;
    }

    @Override
    public String updDamage(NumberModifierDto numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        character.setCurrentHP(character.getCurrentHP() - numberModifierDto.getModifier());
        characterRepo.updateHP(character.getCharName(), character.getCurrentHP());
        String response = gson.toJson(character);
        return response;
    }

}
