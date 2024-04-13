package ru.maleth.mythra.utility.classes.barbarian;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.repo.ClassesRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BarbarianUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;
    private final ClassesRepo classesRepo;

    public List<CharClassAbility> formAbilities(CharClassLevel ccl) {
        Character character = ccl.getCharacter();
        CharClass charClass = classesRepo.findByName("BARBARIAN");
        Integer level = ccl.getClassLevel();
        List<CharClassAbility> ccaList = new ArrayList<>();
        List<Ability> abilities = abilityRepo.findAllByClassLimitByLevel("BARBARIAN", level);
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
                    case "ВАРВАР: ЗАЩИТА БЕЗ ДОСПЕХОВ" -> cca.setNumberOfUses(0);
                    case "ЯРОСТЬ +2" -> {
                        if (ccl.getClassLevel() < 3) {
                            cca.setNumberOfUses(2);
                        } else if (ccl.getClassLevel() >= 3 && ccl.getClassLevel() <= 5) {
                            cca.setNumberOfUses(3);
                        } else if (ccl.getClassLevel() >= 6 && ccl.getClassLevel() <= 8) {
                            cca.setNumberOfUses(4);
                        }
                    }
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
    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;
    private final ClassesRepo classesRepo;

    public List<CharClassAbility> formAbilities(CharClassLevel ccl) {
        Character character = ccl.getCharacter();
        CharClass charClass = classesRepo.findByName("BARBARIAN");
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        int barbLevel = ccl.getClassLevel();
        int numberOfUses = 2;
        String name = "ЯРОСТЬ +2";
        if (barbLevel >= 3 && barbLevel <= 5) {
            numberOfUses = 3;
        } else if (barbLevel >= 6 && barbLevel <= 8) {
            numberOfUses = 4;
        } else if (barbLevel >= 9 && barbLevel <= 11) {
            numberOfUses = 4;
            name = "ЯРОСТЬ +3";
        } else if (barbLevel >= 12 && barbLevel <= 15) {
            numberOfUses = 5;
            name = "ЯРОСТЬ +3";
        } else if (barbLevel == 16) {
            numberOfUses = 5;
            name = "ЯРОСТЬ +4";
        } else if (barbLevel >= 17 && barbLevel <= 19) {
            numberOfUses = 6;
            name = "ЯРОСТЬ +4";
        } else if (barbLevel >= 20) {
            numberOfUses = 0;
            name = "ЯРОСТЬ +4";
        }
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName(name));
        Optional<Ability> abilityPresent2 = Optional.ofNullable(abilityRepo.findByName("ВАРВАР: ЗАЩИТА БЕЗ ДОСПЕХОВ"));
        List<Optional<Ability>> optionalAbilities = new ArrayList<>();
        optionalAbilities.add(abilityPresent);
        optionalAbilities.add(abilityPresent2);
        for (int i = 0; i < optionalAbilities.size(); i++) {
            Ability ability;
            if (optionalAbilities.get(i).isEmpty()) {
                switch (i) {
                    case 0 -> {
                        ability = Ability.builder()
                                .name(name)
                                .description("""
                                        В бою вы сражаетесь с первобытной свирепостью. В свой ход вы можете бонусным действием \
                                        войти в состояние ярости.
                                        В состоянии ярости вы получаете следующие преимущества, если не носите тяжёлую броню:<br>
                                        • Вы совершаете с преимуществом проверки и спасброски Силы.
                                        • Если вы совершаете рукопашную атаку оружием, используя Силу, вы получаете бонус к броску \
                                        урона, соответствующий вашему уровню варвара, как показано в колонке «урон ярости» таблицы «Варвар».
                                        • Вы получаете сопротивление дробящему, колющему и рубящему урону.
                                        Если вы способны накладывать заклинания, то вы не можете накладывать или концентрироваться \
                                        на заклинаниях, пока находитесь в состоянии ярости.
                                        Ваша ярость длится 1 минуту. Она прекращается раньше, если вы потеряли сознание или если вы \
                                        закончили свой ход, не получив урон или не атаковав враждебное по отношению к вам существо \
                                        с момента окончания вашего прошлого хода. Также вы можете прекратить свою ярость бонусным действием.
                                        Если вы впадали в состояние ярости максимальное для вашего уровня количество раз \
                                        (смотрите колонку «ярость»), то вы должны совершить продолжительный отдых, прежде чем \
                                        сможете использовать ярость ещё раз.""")
                                .isActive(true)
                                .requiresRest(true)
                                .typeOfRest(RestEnum.LONG)
                                .abilitySource(AbilityEnum.CLASS)
                                .cost(ActionCostEnum.BONUS_ACTION)
                                .build();
                        abilityRepo.save(ability);
                    }
                    case 1 -> {
                        ability = Ability.builder()
                                .name("ВАРВАР: ЗАЩИТА БЕЗ ДОСПЕХОВ")
                                .description("""
                                        Если вы не носите доспехов, ваш Класс Доспеха равен 10 + модификатор Ловкости + \
                                        модификатор Телосложения. Вы можете использовать щит, не теряя этого преимущества.""")
                                .isActive(false)
                                .requiresRest(false)
                                .typeOfRest(RestEnum.NONE)
                                .abilitySource(AbilityEnum.CLASS)
                                .cost(ActionCostEnum.BLANK)
                                .build();
                        abilityRepo.save(ability);
                    }
                    default -> throw new RuntimeException("Это если не нашлась абилка");
                }
            } else {
                ability = optionalAbilities.get(i).get();
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
        }
        return charClassAbilitiesList;
    }
}*/