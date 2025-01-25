package ru.maleth.mythra.utility.classes.monk;

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
public class MonkUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;
    private final ClassesRepo classesRepo;

    public List<CharClassAbility> formAbilities(CharClassLevel ccl) {
        Character character = ccl.getCharacter();
        CharClass charClass = classesRepo.findByName("MONK");
        Integer level = ccl.getClassLevel();
        List<CharClassAbility> ccaList = new ArrayList<>();
        List<Ability> abilities = abilityRepo.findAllByClassLimitByLevel("MONK", level);
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
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        int numberOfUses = 0;
        List<Optional<Ability>> optionalAbilities = new ArrayList<>();
        Optional<Ability> abilityNoArmorOpt = Optional.ofNullable(abilityRepo.findByName("МОНАХ: ЗАЩИТА БЕЗ ДОСПЕХОВ"));
        Optional<Ability> abilityArtOfFight = Optional.ofNullable(abilityRepo.findByName("БОЕВЫЕ ИСКУССТВА"));
        optionalAbilities.add(abilityNoArmorOpt);
        optionalAbilities.add(abilityArtOfFight);
        for (int i = 0; i < optionalAbilities.size(); i++) {
            Ability ability = new Ability();
            if (optionalAbilities.get(i).isEmpty()) {
                switch (i) {
                    case 0 -> {
                        ability = Ability.builder()
                                .name("МОНАХ: ЗАЩИТА БЕЗ ДОСПЕХОВ")
                                .description("""
                                        Начиная с 1 уровня, если вы не носите ни доспех, ни щит, ваш Класс Доспеха равен 10 + \
                                        модификатор Ловкости + модификатор Мудрости.""")
                                .isActive(false)
                                .requiresRest(false)
                                .typeOfRest(RestEnum.NONE)
                                .abilitySource(AbilityEnum.CLASS)
                                .cost(ActionCostEnum.BLANK)
                                .build();
                        abilityRepo.save(ability);
                    }
                    case 1 -> {
                        ability = Ability.builder()
                                .name("БОЕВЫЕ ИСКУССТВА")
                                .description("""
                                        На 1 уровне ваше знание боевых искусств позволяет вам эффективно использовать \
                                        в бою безоружные удары и оружие монахов — короткие мечи, а также любое простое \
                                        оружие, не имеющее свойств «двуручное» и «тяжёлое».
                                        Если вы безоружны или используете только монашеское оружие, и не носите ни \
                                        доспехов, ни щита, вы получаете следующие преимущества:
                                        • Вы можете использовать Ловкость вместо Силы для бросков атак и урона ваших \
                                        безоружных ударов и атак монашеским оружием.
                                        • Вы можете использовать к4 вместо обычной кости урона ваших безоружных ударов \
                                        или атак монашеским оружием. Эта кость увеличивается с вашим уровнем, как показано \
                                        в колонке «боевые искусства».
                                        • Если в свой ход вы используете действие Атака для безоружной атаки, или атаки \
                                        монашеским оружием, вы можете совершить ещё одну безоружную атаку бонусным \
                                        действием. Например, если вы совершили действие Атака и атаковали боевым \
                                        посохом, вы можете совершить бонусным действием безоружную атаку, при условии, \
                                        что в этом ходу вы ещё не совершали бонусное действие.
                                        Некоторые монастыри используют особые виды монашеского оружия. Например, вы \
                                        можете использовать дубинку в виде двух деревянных брусков, соединённых короткой \
                                        цепью (такое оружие называется нунчаками), или серп с более коротким и прямым \
                                        лезвием (называется камой). Как бы ни называлось ваше монашеское оружие, вы \
                                        используете характеристики из главы 5, соответствующие этому оружию.""")
                                .isActive(false)
                                .requiresRest(false)
                                .typeOfRest(RestEnum.NONE)
                                .abilitySource(AbilityEnum.CLASS)
                                .cost(ActionCostEnum.BLANK)
                                .build();
                        abilityRepo.save(ability);
                    }
                }
            } else if (optionalAbilities.get(i).isPresent()) {
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
}

 */