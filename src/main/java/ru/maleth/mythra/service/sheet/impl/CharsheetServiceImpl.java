package ru.maleth.mythra.service.sheet.impl;

import com.google.gson.Gson;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.AbilityChargeModifierDTO;
import ru.maleth.mythra.dto.AbilityJsonResponseDTO;
import ru.maleth.mythra.dto.mapper.AbilityJsonResponseMapper;
import ru.maleth.mythra.dto.AttributesRaiserDTO;
import ru.maleth.mythra.dto.NumberModifierDTO;
import ru.maleth.mythra.enums.AttribEnum;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.enums.ProfEnum;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.CharClassLevel;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.Proficiency;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.repo.CharClassLevelRepo;
import ru.maleth.mythra.repo.CharRaceAbilityRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.service.character.CharacterService;
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
    private final CharClassLevelRepo charClassLevelRepo;
    private final ClassUtils classUtils;
    private final RaceUtils raceUtils;
    private final CharacterService characterService;
    private final Gson gson;
    private static final String PAGE = "directToPage";

    @Override
    public Map<String, String> getSheet(String userName, String charName) {
        Character character = characterService.findByUserNameAndCharName(userName, charName);
        log.info("Собираем модель персонажа " + character.getCharName() + " для вывода на чаршит!");

        /*
        Создаем мапу, чтобы собирать в нее атрибуты, которые будут выводиться на странице с листо персонажа.
         */
        Map<String, String> attributes = new HashMap<>();

        /*
        Создаем ключевые переменные, чтобы формировать атрибуты
         */

        int experience = character.getExperience();
        int curHitPoints = character.getCurrentHP();
        int maxHitPoints = character.getMaxHP();

        List<CharClassLevel> charClasses = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId());
        StringBuilder charClassesStringBuilder = new StringBuilder();
        if (charClasses.size() > 1) {
            charClassesStringBuilder.append(ClassEnum.valueOf(charClasses.get(0).getCharClass().getName()).getName());
            charClassesStringBuilder.append(" ");
            charClassesStringBuilder.append(charClasses.get(0).getClassLevel());
            for (int i = 1; i < charClasses.size(); i++) {
                charClassesStringBuilder.append(" / ");
                charClassesStringBuilder.append(ClassEnum.valueOf(charClasses.get(i).getCharClass().getName()).getName());
                charClassesStringBuilder.append(" ");
                charClassesStringBuilder.append(charClasses.get(i).getClassLevel());
            }
        } else {
            charClassesStringBuilder.append(ClassEnum.valueOf(charClasses.get(0).getCharClass().getName()).getName());
        }

        String charClassesString = charClassesStringBuilder.toString();

        /*
        Тут переносим атрибуты в мапу. И указываем адрес страницы.
        */
        attributes.put("userName", userName);
        attributes.put("charName", character.getCharName());
        attributes.put("charRace", character.getCharRace().getRaceEnum().getName());
        attributes.put("charClass", charClassesString);
        attributes.put("ac", String.valueOf(character.getArmorClass()));
        attributes.put("speed", String.valueOf(character.getCharRace().getSpeed()));
        attributes.put("initiative", formatMods(character.getInitiative()));
        attributes.put("experience", String.valueOf(experience));
        attributes.put("level", String.valueOf(CharacterCalculator.getLevel(experience)));
        attributes.put("proficiency", formatMods(CharacterCalculator.getProfBonus(experience)));
        attributes.put("curHitPoints", String.valueOf(curHitPoints));
        attributes.put("maxHitPoints", String.valueOf(maxHitPoints));

        if (character.getCharRace().
                isHasDarkvision()) {
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
        List<AbilityJsonResponseDTO> ccaList = classUtils.charClassAbilityFormer(character)
                .stream().map(AbilityJsonResponseMapper::fromCharClassAbility).toList();
        List<AbilityJsonResponseDTO> craList = raceUtils.charRaceAbilityFormer(character)
                .stream().map(AbilityJsonResponseMapper::fromCharRaceAbility).toList();
        List<AbilityJsonResponseDTO> abilList = new ArrayList<>();
        abilList.addAll(ccaList);
        abilList.addAll(craList);
        for (AbilityJsonResponseDTO a : abilList) {
            log.info("Добавлена абилка: {}, кол-во использований: {}", a.getName(), a.getNumberOfCharges());
        }
        String response = gson.toJson(abilList);
        log.info("Cписок абилок для вывода на чаршите персонажа с id {} собран в стрингу Json", charId);
        return response;
    }

    @Override
    public String updAbilityCharge(AbilityChargeModifierDTO abilityChargeModifierDto) {
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
    public String updExp(NumberModifierDTO numberModifierDto) {
        Character character = characterRepo.findById(numberModifierDto.getCharId())
                .orElseThrow(() -> new RuntimeException("Не нашли пользователя"));
        if (CharacterCalculator.getLevel(character.getExperience() + numberModifierDto.getModifier()) > CharacterCalculator.getLevel(character.getExperience())) {
            character.setIsLevelUpReady(true);
        }
        character.setExperience(character.getExperience() + numberModifierDto.getModifier());
        String response = gson.toJson(character);
        characterRepo.updateExp(character.getId(), character.getExperience());
        return response;
    }

    @Override
    public String updHeal(NumberModifierDTO numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        character.setCurrentHP(character.getCurrentHP() + numberModifierDto.getModifier());
        characterRepo.updateHP(character.getCharName(), character.getCurrentHP());
        String response = gson.toJson(character);
        return response;
    }

    @Override
    public String updDamage(NumberModifierDTO numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        character.setCurrentHP(character.getCurrentHP() - numberModifierDto.getModifier());
        characterRepo.updateHP(character.getCharName(), character.getCurrentHP());
        String response = gson.toJson(character);
        return response;
    }

    @Override
    @Transactional
    public void raiseAttributes(AttributesRaiserDTO attributesRaiserDTO) {
        log.info("Обновляем для персонажа '{}' характеристики {}", attributesRaiserDTO.getCharName(), attributesRaiserDTO.getAttrsArray());
        Character character = characterRepo.findById(attributesRaiserDTO.getCharId()).orElseThrow(() -> new RuntimeException("Не нашли по id"));
        for (String attrToRaise : attributesRaiserDTO.getAttrsArray()) {
            switch (attrToRaise) {
                case "strength" -> character.setStrength(character.getStrength()+1);
                case "dexterity" -> character.setDexterity(character.getDexterity()+1);
                case "constitution" -> character.setConstitution(character.getConstitution()+1);
                case "wisdom" -> character.setWisdom(character.getWisdom()+1);
                case "intelligence" -> character.setIntelligence(character.getIntelligence()+1);
                case "charisma" -> character.setCharisma(character.getCharisma()+1);
            }
        }
        characterRepo.save(character);
    }

}
