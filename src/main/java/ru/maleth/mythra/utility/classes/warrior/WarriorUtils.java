package ru.maleth.mythra.utility.classes.warrior;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.repo.ClassesRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WarriorUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;
    private final ClassesRepo classesRepo;

    public List<CharClassAbility> formAbilities(CharClassLevel ccl) {
        Character character = ccl.getCharacter();
        CharClass charClass = classesRepo.findByName("WARRIOR");
        Integer level = ccl.getClassLevel();
        List<CharClassAbility> ccaList = new ArrayList<>();
        List<Ability> abilities = abilityRepo.findAllByClassLimitByLevel("WARRIOR", level);
        for (Ability a : abilities) {
            Optional<CharClassAbility> ccaOptional = Optional.ofNullable(charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), a.getName()));
            CharClassAbility cca;
            if (ccaOptional.isEmpty()) {
                cca = CharClassAbility.builder()
                        .ability(a)
                        .charClass(charClass)
                        .character(character)
                        .build();
                switch (a.getName()) {
                    case "ВТОРОЕ ДЫХАНИЕ" -> cca.setNumberOfUses(1);
                    default -> cca.setNumberOfUses(0);
                }
                charClassAbilityRepo.save(cca);
            } else {
                cca = ccaOptional.get();
            }
            ccaList.add(cca);
        }
        return ccaList;
    }

}
/*
    public List<CharClassAbility> formAbilities(Character character, CharClass charClass) {
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        Ability ability;
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        String name = "ВТОРОЕ ДЫХАНИЕ";
        int numberOfUses = 1;
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName(name));
        if (abilityPresent.isEmpty()) {
            ability = Ability.builder()
                    .name(name)
                    .description("""
                            Вы обладаете ограниченным источником выносливости, которым можете воспользоваться, чтобы \
                            уберечь себя. В свой ход вы можете бонусным действием восстановить хиты в размере 1к10 + \
                            уровень воина.
                            Использовав это умение, вы должны завершить короткий либо продолжительный отдых, чтобы \
                            получить возможность использовать его снова.""")
                    .isActive(true)
                    .requiresRest(true)
                    .typeOfRest(RestEnum.SHORT)
                    .abilitySource(AbilityEnum.CLASS)
                    .cost(ActionCostEnum.BONUS_ACTION)
                    .build();
            abilityRepo.save(ability);
        } else {
            ability = abilityPresent.get();
        }
        Optional<CharClassAbility> ccaOptional = Optional.ofNullable(charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), ability.getName()));
        CharClassAbility charClassAbility;
        if (ccaOptional.isEmpty()) {
            charClassAbility = CharClassAbility.builder()
                    .character(character)
                    .ability(ability)
                    .charClass(charClass)
                    .numberOfUses(numberOfUses)
                    .build();
            charClassAbilityRepo.save(charClassAbility);
        } else {
            charClassAbility = ccaOptional.get();
        }
        charClassAbilitiesList.add(charClassAbility);
        return charClassAbilitiesList;
    }
    */