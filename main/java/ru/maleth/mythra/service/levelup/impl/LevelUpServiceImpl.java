package ru.maleth.mythra.service.levelup.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import ru.maleth.mythra.dto.CharClassToLevelUp;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.model.CharClassLevel;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.CharClassLevelRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.service.levelup.LevelUpService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class LevelUpServiceImpl implements LevelUpService {

    private final CharacterRepo characterRepo;
    private final CharClassLevelRepo charClassLevelRepo;
    private static final String PAGE = "directToPage";

    @Override
    public Map<String, String> formLvlUpPage(String userName, String charName) {
        Map<String, String> attributes = new HashMap<>();
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).orElseThrow(()
                -> new RuntimeException("Не нашли персонажа в таблице Персонажи по имени " + charName));
        List<CharClassLevel> cclList = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId());
        List<String> characterClassesWithLevels = new ArrayList<>();
        for (CharClassLevel charClassLevel : cclList) {
            String className = ClassEnum.valueOf(charClassLevel.getCharClass().getName()).getName();
            characterClassesWithLevels.add(className);
            characterClassesWithLevels.add(String.valueOf(charClassLevel.getClassLevel()));
        }
        Gson gson = new Gson();
        String characterClassesWithLevelsJson = gson.toJson(characterClassesWithLevels);
        attributes.put("array", characterClassesWithLevelsJson);
        attributes.put("charName", charName);
        attributes.put("size", String.valueOf(characterClassesWithLevels.size()));
        attributes.put(PAGE, "levelup");
        return attributes;
    }

    @Override
    public void levelUp(CharClassToLevelUp charClassToLevelUp) {
        Character character = characterRepo.findById(charClassToLevelUp.getCharId()).orElseThrow(()
                -> new RuntimeException("Не нашли записи в таблице Персонажи подходящей под условие фильтра id персонажа = " + charClassToLevelUp.getCharId()));
        log.info("Нашли персонажа с именем {} и id {} для повышения уровня", character.getCharName(), character.getId());
        CharClassLevel ccl = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId())
                .stream()
                .filter(c -> c.getCharClass().getName().equals(ClassEnum.getClassByName(charClassToLevelUp.getCharClassToLevelUp()).toString()))
                .findFirst().orElseThrow(()
                        -> new RuntimeException("Не нашли записи в таблице Персонаж-Класс-Уровень подходящей под условие фильтра класс персонажа = " + charClassToLevelUp.getCharClassToLevelUp()));
        log.info("Нашли запись в таблице Персонаж-Класс-Уровень для персонажа {}, соответствующую классу отмеченному на повышение уровня {}",
                character.getCharName(),
                charClassToLevelUp.getCharClassToLevelUp());
        ccl.setClassLevel(ccl.getClassLevel() + 1);
        log.info("Изменяем поле 'уровень' класса персонажа '{}' в таблице Персонаж-Класс-Уровень c '{}' до '{}'", ccl.getCharClass().getName(), ccl.getClassLevel() - 1, ccl.getClassLevel());
        character.setIsLevelUpReady(false);
        log.info("Меняем запись в таблице персонажа '{}' isLevelUpReady на false (assert: {})", character.getCharName(), character.getIsLevelUpReady());
        characterRepo.save(character);
        charClassLevelRepo.save(ccl);
    }
}
