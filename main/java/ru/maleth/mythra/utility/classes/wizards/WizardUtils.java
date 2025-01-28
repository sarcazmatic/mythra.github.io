package ru.maleth.mythra.utility.classes.wizards;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.utility.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class WizardUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(Character character, CharClass charClass) {
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        Ability ability;
        String name = "МАГИЧЕСКОЕ ВОССТАНОВЛЕНИЕ";
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        int numberOfUses = Math.max((int) Math.ceil(characterLevel / 2), 1);
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName(name));
        if (abilityPresent.isEmpty()) {
            ability = Ability.builder()
                    .name(name)
                    .description("""
                            Вы знаете как восстанавливать часть магической энергии, изучая книгу заклинаний. Один раз в \
                            день, когда вы заканчиваете короткий отдых, вы можете восстановить часть использованных ячеек \
                            заклинаний. Ячейки заклинаний могут иметь суммарный уровень, который не превышает половину \
                            уровня вашего волшебника (округляя в большую сторону), и ни одна из ячеек не может быть \
                            шестого уровня или выше.
                            Например, если вы волшебник 4 уровня, вы можете восстановить ячейки заклинаний с суммой \
                            уровней не больше двух. Вы можете восстановить одну ячейку заклинаний 2 уровня, или две \
                            ячейки заклинаний 1 уровня.""")
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

}
