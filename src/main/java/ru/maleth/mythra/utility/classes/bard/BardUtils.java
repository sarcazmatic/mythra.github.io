package ru.maleth.mythra.utility.classes.bard;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.utility.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class BardUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(CharClassLevel ccl) {
        log.info("Собираем абилки персонажа '{}' для класса '{}' для вывода на чаршит", ccl.getCharacter().getCharName(), ccl.getCharClass().getName());
        Character character = ccl.getCharacter();
        CharClass charClass = ccl.getCharClass();
        Integer level = ccl.getClassLevel();
        List<CharClassAbility> ccaList = new ArrayList<>();
        List<Ability> abilitiesUnfiltered = abilityRepo.findAllByClassLimitByLevel(ccl.getCharClass().getName(), level);
        List<Ability> abilitiesFiltered = filterList(level, abilitiesUnfiltered);
        log.info("Собираем список абилок, который должен быть у класса '{}' на уровне {}", ccl.getCharClass().getName(), level);
        for (Ability a : abilitiesFiltered) {
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
                    case "ВДОХНОВЕНИЕ БАРДА (к6)" ->
                            cca.setNumberOfUses(Math.max(CharacterCalculator.calculateAttributeModifier(character.getCharisma()), 1));
                    case "ВДОХНОВЕНИЕ БАРДА (к8)" -> {
                        CharClassAbility ccaToDelete = charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), "ВДОХНОВЕНИЕ БАРДА (к6)");
                        cca.setNumberOfUses(ccaToDelete.getNumberOfUses());
                        charClassAbilityRepo.delete(ccaToDelete);
                    }
                    case "ВДОХНОВЕНИЕ БАРДА (к10)" -> {
                        CharClassAbility ccaToDelete = charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), "ВДОХНОВЕНИЕ БАРДА (к8)");
                        cca.setNumberOfUses(ccaToDelete.getNumberOfUses());
                        charClassAbilityRepo.delete(ccaToDelete);
                    }
                    case "ВДОХНОВЕНИЕ БАРДА (к12)" -> {
                        CharClassAbility ccaToDelete = charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), "ВДОХНОВЕНИЕ БАРДА (к10)");
                        cca.setNumberOfUses(ccaToDelete.getNumberOfUses());
                        charClassAbilityRepo.delete(ccaToDelete);
                    }
                    default -> cca.setNumberOfUses(0);
                }
                charClassAbilityRepo.save(cca);
            } else {
                log.info("Абилка '{}' уже есть у класса '{}' на уровне {}", a.getName(), ccl.getCharClass().getName(), level);
                cca = ccaOptional.get();
            }
            ccaList.add(cca);
        }
        log.info("Возвращаем список абилок класса '{}' на уровне {}, состоящий из {} элементов", ccl.getCharClass().getName(), level, ccaList.size());
        return ccaList;
    }

    private List<Ability> filterList(Integer level, List<Ability> abilityListUnfiltered) {
        List<Ability> abilityListFiltered;
        if (level >= 17) {
            abilityListFiltered = new ArrayList<>(abilityListUnfiltered.stream().filter(a -> !a.getName().equals("ПЕСНЬ ОТДЫХА (к10)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к10)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к8)"))
                    .filter(a -> !a.getName().equals("ПЕСНЬ ОТДЫХА (к6)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к6)"))
                    .toList());
        } else if (level >= 15) {
            abilityListFiltered = new ArrayList<>(abilityListUnfiltered.stream().filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к10)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к8)"))
                    .filter(a -> !a.getName().equals("ПЕСНЬ ОТДЫХА (к6)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к6)"))
                    .toList());
        } else if (level >= 13) {
            abilityListFiltered = new ArrayList<>(abilityListUnfiltered.stream().filter(cca -> !cca.getName().equals("ПЕСНЬ ОТДЫХА (к8)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к8)"))
                    .filter(a -> !a.getName().equals("ПЕСНЬ ОТДЫХА (к6)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к6)"))
                    .toList());
        } else if (level >= 10) {
            abilityListFiltered = new ArrayList<>(abilityListUnfiltered.stream().filter(cca -> !cca.getName().equals("ВДОХНОВЕНИЕ БАРДА (к8)"))
                    .filter(a -> !a.getName().equals("ПЕСНЬ ОТДЫХА (к6)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к6)"))
                    .toList());
        } else if (level >= 9) {
            abilityListFiltered = new ArrayList<>(abilityListUnfiltered.stream().filter(cca -> !cca.getName().equals("ПЕСНЬ ОТДЫХА (к6)"))
                    .filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к6)"))
                    .toList());
        } else if (level >= 5) {
            abilityListFiltered = new ArrayList<>(abilityListUnfiltered.stream().filter(a -> !a.getName().equals("ВДОХНОВЕНИЕ БАРДА (к6)"))
                    .toList());
        } else {
            abilityListFiltered = new ArrayList<>(abilityListUnfiltered);
        }
        return abilityListFiltered;
    }

}
