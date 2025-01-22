package ru.maleth.mythra.service.character.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.CharacterFullDto;
import ru.maleth.mythra.dto.NewCharacterDto;
import ru.maleth.mythra.dto.NewCharacterFullDto;
import ru.maleth.mythra.enums.*;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.*;
import ru.maleth.mythra.utility.CharacterCalculator;
import ru.maleth.mythra.service.character.CharacterCreationService;
import ru.maleth.mythra.utility.classes.ClassUtils;
import ru.maleth.mythra.utility.races.RaceUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharacterCreationServiceImpl implements CharacterCreationService {

    private final CharacterRepo characterRepo;
    private final RaceRepo raceRepo;
    private final ClassesRepo classesRepo;
    private final ProficiencyRepo proficiencyRepo;
    private final CharClassLevelRepo charClassLevelRepo;
    private final UserRepo userRepo;


    private static final String PAGE = "directToPage";

    @Override
    //срабатывает на шаге /register -> /attributes
    public Map<String, String> goToAttributes(NewCharacterDto newCharacterDto) {
        log.info("Собираем атрибуты для персонажа " + newCharacterDto.getCharName() + "!");

        String charName = newCharacterDto.getCharName();
        String charClass = newCharacterDto.getCharClass();
        String charRace = newCharacterDto.getCharRace();
        String charSubrace = newCharacterDto.getCharSubrace();

        /*
        Создаем мапу, чтобы собирать в нее атрибуты – ее в конце и передадим в контролер, чтобы тот отобразил
        атрибуты на странице.
         */
        Map<String, String> attributes = new HashMap<>();

        /*
        Суем в мапу имя.
         */
        attributes.put("charName", charName);

        /*
        Здесь мы заменяем переданные сюда названия классов:
        Из "Кровавый-охотник" делаем "Кровавый охотник", разделяем на два на дефисе ('-') и сохраняем в двух переменных,
        чтобы передать на страницу. Если дефиса нет, вторая переменная остается пустой.
        */
        if (charClass.contains("-")) {
            String[] charClassArray = charClass.split("-");
            String charClassOne = charClassArray[0];
            attributes.put("charClassOne", charClassOne);
            String charClassTwo = charClassArray[1];
            attributes.put("charClassTwo", " " + charClassTwo);
        } else {
            attributes.put("charClassOne", charClass);
        }

        /*
        Здесь мы заменяем сабрасы на расы, также заботясь о названиях.
        Например "Аасимар-каратель" првращается в "Аасимар каратель".
        И заодно собираем все в мапу. Это неидеально, лучше бы переменные завести.
        */
        if (charRace.equals("Аасимар")
                || charRace.equals("Гит")
                || charRace.equals("Гном")
                || charRace.equals("Дварф")
                || charRace.equals("Драконорожденный")
                || charRace.equals("Полурослик")
                || charRace.equals("Эльф")) {
            if (charSubrace.contains("-")) {
                String[] charRaceArray = charSubrace.split("-");
                String charRaceOne = charRaceArray[0];
                attributes.put("charRaceOne", charRaceOne);
                String charRaceTwo = charRaceArray[1];
                attributes.put("charRaceTwo", " " + charRaceTwo);
            } else {
                attributes.put("charRaceOne", charSubrace);
            }
        } else {
            attributes.put("charRaceOne", charRace);
        }

        /*
        Здесь передаем мапу с атрибутами в контролер и указываем, на какую страницу будет осуществляться переход.
        */
        attributes.put(PAGE, "attributes");

        log.info("Атрибуты персонажа '{}' собраны. Следующая -- страницы навыков", newCharacterDto.getCharName());

        return attributes;
    }

    @Override
    //срабатывает на шаге /attributes -> /skills
    public Map<String, String> goToSkills(NewCharacterFullDto newCharacterFullDto) {
        log.info("Собираем навыки для персонажа " + newCharacterFullDto.getCharName() + "!");

        String charName = newCharacterFullDto.getCharName();
        String charClass = newCharacterFullDto.getCharClass();
        String charRace = newCharacterFullDto.getCharRace();
        int strength = newCharacterFullDto.getStrength();
        int dexterity = newCharacterFullDto.getDexterity();
        int constitution = newCharacterFullDto.getConstitution();
        int intelligence = newCharacterFullDto.getIntelligence();
        int wisdom = newCharacterFullDto.getWisdom();
        int charisma = newCharacterFullDto.getCharisma();

        /*
        Создаем мапу, чтобы собирать в нее атрибуты.
         */
        Map<String, String> attributes = new HashMap<>();

        /*
        Собираем переменные, которые будем передавать в мапу: hitPoints и race, которая нам нужна, чтобы
        верно высчитать модификаторы атрибутов.
         */
        int hitPoints = CharacterCalculator.calculateDieHit(ClassEnum.getClassByName(charClass)) +
                CharacterCalculator.calculateAttributeModifier(constitution);
        Race race = raceRepo.findByRaceEnum(RaceEnum.getRaceByName(charRace)); //тутут
        int strengthAtr = strength + race.getStrengthBonus();
        int dexterityAtr = dexterity + race.getDexterityBonus();
        int constitutionAtr = constitution + race.getConstitutionBonus();
        int intelligenceAtr = intelligence + race.getIntelligenceBonus();
        int wisdomAtr = wisdom + race.getWisdomBonus();
        int charismaAtr = charisma + race.getCharismaBonus();

        /*
        Передаем в мапу переменные и стринг со страницей, на которую переводим. Отдаем в контролер.
         */
        attributes.put("charName", charName);
        attributes.put("charRace", charRace);
        attributes.put("charClass", charClass);
        attributes.put("hitPoints", String.valueOf(hitPoints));
        attributes.put("strength", String.valueOf(strengthAtr));
        attributes.put("dexterity", String.valueOf(dexterityAtr));
        attributes.put("constitution", String.valueOf(constitutionAtr));
        attributes.put("intelligence", String.valueOf(intelligenceAtr));
        attributes.put("wisdom", String.valueOf(wisdomAtr));
        attributes.put("charisma", String.valueOf(charismaAtr));
        attributes.put("strengthMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(strengthAtr)));
        attributes.put("dexterityMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(dexterityAtr)));
        attributes.put("intelligenceMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(intelligenceAtr)));
        attributes.put("wisdomMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(wisdomAtr)));
        attributes.put("charismaMod", String.valueOf(CharacterCalculator.calculateAttributeModifier(charismaAtr)));

        attributes.put(PAGE, "skills");

        log.info("Навыки персонажа '{}' собраны. Следующая -- чаршит", newCharacterFullDto.getCharName());

        return attributes;
    }

    @Override
    //срабатывает ПЕРВЫМ на шаге /skills -> /charsheet
    public Character createCharacter(String userName, CharacterFullDto characterFullDto) {
        log.info("Создаем персонажа для пользователя под именем '{}'", userName);

        Optional<Character> characterOptional = characterRepo.findByCreator_NameAndCharName(userName, characterFullDto.getCharName());
        if (characterOptional.isPresent()) {
            return characterOptional.get();
        }

        List<String> profs = characterFullDto.getProfs().stream().map(p
                -> p.toUpperCase().replace('-', '_')).toList();

        /*
        Тут создаем нового персонажа. Для начала создаем SET (в котором не повторяются данные).
        В него отправляем все специализации персонажа. Чтобы все было чин по чину, мы находим специализации
        в репозитории по имени, которое берем в списе profs
        */
        Set<Proficiency> proficiencies = new HashSet<>();
        for (String s : profs) {
            try {
                proficiencies.add(proficiencyRepo.findByName(s));
            } catch (RuntimeException e) {
                throw new RuntimeException("Нет такого владения");
            }
        }

        /*
        Теперь находим класс персонажа (на этом этапе класс у персонажа может быть только один, но далее возможно
        мультиклассирование, и нужно будет куда-то засовывать доп классы в Set).
        В сет засовываем класс из репозитория; в репозитории класс находим по названию.
        */
        CharClass charClass = classesRepo.findByName(ClassEnum.getClassByName(characterFullDto.getCharClass()).toString());

        /*
        Тут просто - находим в репозитории расу и присваиваем персонажу.
        */
        Race race = raceRepo.findByRaceEnum(RaceEnum.getRaceByName(characterFullDto.getCharRace()));

        /*
        Тут находим юзера по имени, переданному из контроера
        */
        User user = userRepo.findByName(userName).orElseThrow(() -> new RuntimeException("Нет такого юзера"));

        /*
        Ну и создаем персонажа через @Builder
        */
        Character character = characterRepo.save(Character.builder()
                .charName(characterFullDto.getCharName())
                .charRace(race)
                .strength(characterFullDto.getStrength())
                .dexterity(characterFullDto.getDexterity())
                .constitution(characterFullDto.getConstitution())
                .intelligence(characterFullDto.getIntelligence())
                .wisdom(characterFullDto.getWisdom())
                .charisma(characterFullDto.getCharisma())
                .maxHP(characterFullDto.getHitPoints())
                .currentHP(characterFullDto.getHitPoints())
                .experience(0)
                .isLevelUpReady(false)
                .armorClass(10 + CharacterCalculator.calculateAttributeModifier(characterFullDto.getDexterity()))
                .initiative(CharacterCalculator.calculateAttributeModifier(characterFullDto.getDexterity()))
                .proficiencies(proficiencies)
                .creator(user)
                .mainClass(charClass)
                .build());
        CharClassLevel charClassLevel = CharClassLevel.builder()
                .character(character)
                .charClass(charClass)
                .classLevel(1)
                .build();
        charClassLevelRepo.save(charClassLevel);

        log.info("Персонаж под именем " + character.getCharName() + " для пользователя " + character.getCreator().getName() + " создан!");

        return character;
    }

    @Override
    //срабатывает ВТОРЫМ на шаге /skills -> /charsheet
    public Map<String, String> formSheet(String userName, CharacterFullDto characterFullDto) {
        Character character = createCharacter(userName, characterFullDto);
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
        Создаем Set, и засовываем сюда названия всех профишиенси, отмеченных галочкой на странице skills.jsp.
        <input type="checkbox" id="acrobatics" name="profs" value="acrobatics"> -- так как у всех чекбоксов имя profs,
        то их values, а именно названия сами профишинси, будут переданы в списке под названием profs
         */
        Set<String> characterProficiencies = character.getProficiencies().stream().map(p
                -> p.getName().toUpperCase().replace('-', '_')).collect(Collectors.toSet());

        /*
        Собираем переменные, которые будем передавать в мапу
        allProficienciesList – список всех имеющихся профишиенси (специализаций в навыках) в формате SLEIGHT_OF_HAND.
        Чтобы сравнить со специализациями в списке profs, содержимое profs нужно перевести в формат NN_NN.
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

        /*
        Проходимся по списку спасбросков, в которых есть специализация и выставляем повышенное значение.
        Проставили – и засовываем в мапу в формате(strengthsave, 10).

        ТУТ МОЖНО ПОСТУПИТЬ, КАК С НАВЫКАМИ – собрать список атрибутов и смотреть, в каких специализируются
        персонаж, но я сделал иначе – заполнил сперва не модифицированно, а затем переписал.
        */
        attributes.put("strengthsave", formatMods(CharacterCalculator.calculateAttributeModifier(strength)));
        attributes.put("dexteritysave", formatMods(CharacterCalculator.calculateAttributeModifier(dexterity)));
        attributes.put("constitutionsave", formatMods(CharacterCalculator.calculateAttributeModifier(constitution)));
        attributes.put("intelligencesave", formatMods(CharacterCalculator.calculateAttributeModifier(intelligence)));
        attributes.put("wisdomsave", formatMods(CharacterCalculator.calculateAttributeModifier(wisdom)));
        attributes.put("charismasave", formatMods(CharacterCalculator.calculateAttributeModifier(charisma)));
        for (int k = 0; k < savingThrows.size(); k++) {
            switch (savingThrows.get(k).toString()) {
                case "STRENGTH" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(strength) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "DEXTERITY" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(dexterity) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "CONSTITUTION" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(constitution) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "INTELLIGENCE" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(intelligence) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "WISDOM" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(wisdom) + CharacterCalculator.getProfBonus(character.getExperience())));
                case "CHARISMA" -> attributes.put(savingThrows.get(k).toString().toLowerCase() + "save",
                        formatMods(CharacterCalculator.calculateAttributeModifier(charisma) + CharacterCalculator.getProfBonus(character.getExperience())));
                default -> throw new RuntimeException("Не получилось проставить спасброски");
            }
        }

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

}