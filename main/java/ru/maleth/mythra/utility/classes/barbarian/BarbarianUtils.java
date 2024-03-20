package ru.maleth.mythra.utility.classes.barbarian;

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
public class BarbarianUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(List<CharClassAbility> charClassAbilitiesList, Ability ability,
                                                Character character, CharClass charClass) {
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        int numberOfUses = 2;
        if (characterLevel >= 3 && characterLevel <= 5) {
            numberOfUses = 3;
        } else if (characterLevel >= 6 && characterLevel <= 11) {
            numberOfUses = 4;
        } else if (characterLevel >= 12 && characterLevel <= 16) {
            numberOfUses = 5;
        } else if (characterLevel >= 17 && characterLevel <= 19) {
            numberOfUses = 6;
        } else if (characterLevel >= 20) {
            numberOfUses = 1000;
        }
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName("ЯРОСТЬ"));
        if (abilityPresent.isEmpty()) {
            ability = Ability.builder()
                    .name("ЯРОСТЬ")
                    .classLevel(characterLevel)
                    .description("""
                            В бою вы сражаетесь с первобытной свирепостью. В свой ход вы можете бонусным действием \
                            войти в состояние ярости.<br>
                            В состоянии ярости вы получаете следующие преимущества, если не носите тяжёлую броню:<br>
                            – Вы совершаете с преимуществом проверки и спасброски Силы.<br>
                            – Если вы совершаете рукопашную атаку оружием, используя Силу, вы получаете бонус к броску \
                            урона, соответствующий вашему уровню варвара, как показано в колонке «урон ярости» таблицы «Варвар».<br>
                            – Вы получаете сопротивление дробящему, колющему и рубящему урону.<br>
                            Если вы способны накладывать заклинания, то вы не можете накладывать или концентрироваться \
                            на заклинаниях, пока находитесь в состоянии ярости.<br>
                            Ваша ярость длится 1 минуту. Она прекращается раньше, если вы потеряли сознание или если вы \
                            закончили свой ход, не получив урон или не атаковав враждебное по отношению к вам существо \
                            с момента окончания вашего прошлого хода. Также вы можете прекратить свою ярость бонусным действием.<br>
                            Если вы впадали в состояние ярости максимальное для вашего уровня количество раз (смотрите колонку «ярость»), то вы должны совершить продолжительный отдых, прежде чем сможете использовать ярость ещё раз.""")
                    .isActive(true)
                    .requiresRest(true)
                    .typeOfRest(RestEnum.LONG)
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