package ru.maleth.mythra.utility.classes.warrior;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WarriorUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(List<CharClassAbility> charClassAbilitiesList, Ability ability,
                                                Character character, CharClass charClass) {
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        String name = "ВТОРОЕ ДЫХАНИЕ (1К10 + " + characterLevel + ")";
        int numberOfUses = 1;
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName(name));
        if (abilityPresent.isEmpty()) {
            ability = Ability.builder()
                    .name(name)
                    .classLevel(1)
                    .description("""
                            Вы обладаете ограниченным источником выносливости, которым можете воспользоваться, чтобы \
                            уберечь себя. В свой ход вы можете бонусным действием восстановить хиты в размере 1к10 + \
                            ваш уровень воина.<br>
                            Использовав это умение, вы должны завершить короткий либо продолжительный отдых, чтобы \
                            получить возможность использовать его снова.""")
                    .isActive(true)
                    .requiresRest(true)
                    .typeOfRest(RestEnum.SHORT)
                    .charClass(charClass)
                    .cost(ActionCostEnum.BONUS_ACTION)
                    .build();
            abilityRepo.save(ability);
        } else {
            ability = abilityPresent.get();
        }
        Optional<CharClassAbility> ccaOptional = Optional.ofNullable(charClassAbilityRepo.findByCharacter_CharNameAndAbility_NameAndCharClass_Name(character.getCharName(), ability.getName(), charClass.getName()));
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

}
