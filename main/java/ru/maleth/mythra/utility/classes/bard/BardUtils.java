package ru.maleth.mythra.utility.classes.bard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BardUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(Character character, CharClass charClass) {
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        Ability ability;
        String name;
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        if (characterLevel >= 1 && characterLevel <= 5) {
            name = "ВДОХНОВЕНИЕ БАРДА (к6)";
        } else if (characterLevel >= 5 && characterLevel <= 9) {
            name = "ВДОХНОВЕНИЕ БАРДА (к8)";
        } else if (characterLevel >= 10 && characterLevel <= 14) {
            name = "ВДОХНОВЕНИЕ БАРДА (к10)";
        } else {
            name = "ВДОХНОВЕНИЕ БАРДА (к12)";
        }
        int numberOfUses = Math.max(CharacterCalculator.calculateAttributeModifier(character.getCharisma()), 1);
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName(name));
        if (abilityPresent.isEmpty()) {
            ability = Ability.builder()
                    .name(name)
                    .description("""
                                Своими словами или музыкой вы можете вдохновлять других. Для этого вы должны бонусным \
                                действием выбрать одно существо, отличное от вас, в пределах 60 футов, которое может вас \
                                слышать. Это существо получает кость бардовского вдохновения — к6.
                                В течение следующих 10 минут это существо может один раз бросить эту кость и добавить \
                                результат к проверке характеристики, броску атаки или спасброску, который оно совершает. \
                                Существо может принять решение о броске кости вдохновения уже после броска к20, но должно \
                                сделать это прежде, чем Мастер объявит результат броска. Как только кость бардовского \
                                вдохновения брошена, она исчезает. Существо может иметь только одну такую кость одновременно.
                                Вы можете использовать это умение количество раз, равное модификатору вашей Харизмы, но \
                                как минимум один раз. Потраченные использования этого умения восстанавливаются после \
                                продолжительного отдыха.
                                Ваша кость бардовского вдохновения изменяется с ростом вашего уровня в этом классе. Она \
                                становится к8 на 5-м уровне, к10 на 10-м уровне и к12 на 15-м уровне.""")
                    .isActive(true)
                    .requiresRest(true)
                    .typeOfRest(RestEnum.LONG)
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
