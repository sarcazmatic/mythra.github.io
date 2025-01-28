package ru.maleth.mythra.utility.classes.barbarian;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.repo.ClassesRepo;
import ru.maleth.mythra.utility.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class BarbarianUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(CharClassLevel ccl) {
        log.info("Собираем абилки персонажа '{}' для класса '{}' для вывода на чаршит", ccl.getCharacter().getCharName(), ccl.getCharClass().getName());
        Character character = ccl.getCharacter();
        CharClass charClass = ccl.getCharClass();
        Integer level = ccl.getClassLevel();
        List<CharClassAbility> ccaList = new ArrayList<>();
        List<Ability> abilitiesUnfiltered = abilityRepo.findAllByClassLimitByLevel(ccl.getCharClass().getName(), level);
        log.info("Собираем список абилок, который должен быть у класса '{}' на уровне {}", ccl.getCharClass().getName(), level);
        for (Ability a : abilitiesUnfiltered) {
            Optional<CharClassAbility> ccaOptional = Optional.ofNullable(charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), a.getName()));
            CharClassAbility cca;
            if (ccaOptional.isEmpty()) {
                log.info("Абилка '{}' должна быть у класса '{}' на уровне {}, но в списке нет. Создаем", a.getName(), ccl.getCharClass().getName(), level);
                cca = CharClassAbility.builder()
                        .ability(a)
                        .charClass(charClass)
                        .character(character)
                        .build();
                switch (a.getName()) {
                    case "ЯРОСТЬ" -> cca.setNumberOfUses(2);
                    default -> cca.setNumberOfUses(0);
                }
                System.out.println(cca);
                charClassAbilityRepo.save(cca);
            } else {
                log.info("Абилка '{}' уже есть у класса '{}' на уровне {}", a.getName(), ccl.getCharClass().getName(), level);
                if (ccaOptional.get().getAbility().getName().equals("ЯРОСТЬ")) {
                    cca = rageNumberOfUsesUpdater(ccl, level);
                } else {
                    cca = ccaOptional.get();
                }
            }
            ccaList.add(cca);
        }
        log.info("Возвращаем список абилок класса '{}' на уровне {}, состоящий из {} элементов", ccl.getCharClass().getName(), level, ccaList.size());
        return ccaList;
    }

    CharClassAbility rageNumberOfUsesUpdater(CharClassLevel ccl, Integer barbLevel) {
        CharClassAbility rage = charClassAbilityRepo.findByCharacter_IdAndAbility_Name(ccl.getCharacter().getId(), "ЯРОСТЬ");
        Integer numberOfUses;
        if (barbLevel >= 3 && barbLevel <= 5) {
            numberOfUses = 3;
        } else if (barbLevel >= 6 && barbLevel <= 8) {
            numberOfUses = 4;
        } else if (barbLevel >= 9 && barbLevel <= 11) {
            numberOfUses = 4;
        } else if (barbLevel >= 12 && barbLevel <= 15) {
            numberOfUses = 5;
        } else if (barbLevel == 16) {
            numberOfUses = 5;
        } else if (barbLevel >= 17 && barbLevel <= 19) {
            numberOfUses = 6;
        } else if (barbLevel >= 20) {
            numberOfUses = 1000;
        } else {
            numberOfUses = 2;
        }
        rage.setNumberOfUses(numberOfUses);
        charClassAbilityRepo.save(rage);
        return rage;
    }

}