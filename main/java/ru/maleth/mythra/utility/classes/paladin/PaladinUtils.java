package ru.maleth.mythra.utility.classes.paladin;

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
public class PaladinUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;
    private final ClassesRepo classesRepo;

    public List<CharClassAbility> formAbilities(CharClassLevel ccl) {
        Character character = ccl.getCharacter();
        CharClass charClass = classesRepo.findByName("PALADIN");
        Integer level = ccl.getClassLevel();
        List<CharClassAbility> ccaList = new ArrayList<>();
        List<Ability> abilities = abilityRepo.findAllByClassLimitByLevel("PALADIN", level);
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
                    case "БОЖЕСТВЕННОЕ ЧУВСТВО" -> cca.setNumberOfUses(Math.max(1 + CharacterCalculator.calculateAttributeModifier(character.getCharisma()), 1));
                    case "НАЛОЖЕНИЕ РУК" -> cca.setNumberOfUses(ccl.getClassLevel() * 5);
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
        Optional<Ability> abilityNoArmorOpt = Optional.ofNullable(abilityRepo.findByName("БОЖЕСТВЕННОЕ ЧУВСТВО"));
        Optional<Ability> abilityArtOfFight = Optional.ofNullable(abilityRepo.findByName("НАЛОЖЕНИЕ РУК"));
        optionalAbilities.add(abilityNoArmorOpt);
        optionalAbilities.add(abilityArtOfFight);
        for (int i = 0; i < optionalAbilities.size(); i++) {
            Ability ability = new Ability();
            if (optionalAbilities.get(i).isEmpty()) {
                switch (i) {
                    case 0 -> {
                        numberOfUses = Math.max(1 + CharacterCalculator.calculateAttributeModifier(character.getCharisma()), 1);
                        ability = Ability.builder()
                                .name("БОЖЕСТВЕННОЕ ЧУВСТВО")
                                .description("""
                                        Присутствие сильного зла воспринимается вашими чувствами как неприятный запах, \
                                        а могущественное добро звучит как небесная музыка в ваших ушах. Вы можете \
                                        действием открыть своё сознание для обнаружения таких сил. Вы до конца своего \
                                        следующего хода знаете местоположение всех исчадий, небожителей и нежити в \
                                        пределах 60 футов, не имеющих полного укрытия. Вы знаете тип (исчадие, \
                                        небожитель, нежить) любого существа, чьё присутствие вы чувствуете, но не можете \
                                        определить, кто это конкретно (например, вампир Граф Страд фон Зарович). В этом \
                                        же радиусе вы также обнаруживаете присутствие мест и предметов, которые были \
                                        освящены или осквернены, например, заклинанием святилище.
                                        Вы можете использовать это умение количество раз, равное 1 + модификатор \
                                        Харизмы. Когда вы заканчиваете продолжительный отдых, вы восстанавливаете все \
                                        потраченные использования.""")
                                .isActive(true)
                                .requiresRest(true)
                                .typeOfRest(RestEnum.LONG)
                                .abilitySource(AbilityEnum.CLASS)
                                .cost(ActionCostEnum.ACTION)
                                .charClass(charClass)
                                .race(null)
                                .levelAvailable(1)
                                .build();
                        abilityRepo.save(ability);
                    }
                    case 1 -> {
                        numberOfUses = characterLevel * 5;
                        ability = Ability.builder()
                                .name("НАЛОЖЕНИЕ РУК")
                                .description("""
                                        Ваше благословенное касание может лечить раны. У вас есть запас целительной \
                                        силы, который восстанавливается после продолжительного отдыха. При помощи этого \
                                        запаса вы можете восстанавливать количество хитов, равное уровню паладина, умноженному на 5.
                                        Вы можете действием коснуться существа и, зачерпнув силу из запаса, восстановить \
                                        количество хитов этого существа на любое число, вплоть до максимума, оставшегося \
                                        в вашем запасе.
                                        В качестве альтернативы, вы можете потрать 5 хитов из вашего запаса хитов для \
                                        излечения цели от одной болезни или одного действующего на неё яда. Вы можете \
                                        устранить несколько эффектов болезни и ядов одним использованием Наложения рук, \
                                        тратя хиты отдельно для каждого эффекта.
                                        Это умение не оказывает никакого эффекта на нежить и конструктов.""")
                                .isActive(true)
                                .requiresRest(true)
                                .typeOfRest(RestEnum.LONG)
                                .abilitySource(AbilityEnum.CLASS)
                                .cost(ActionCostEnum.ACTION)
                                .charClass(charClass)
                                .race(null)
                                .levelAvailable(1)
                                .build();
                        abilityRepo.save(ability);
                    }
                }
            } else if (optionalAbilities.get(i).isPresent()) {
                ability = optionalAbilities.get(i).get();
                switch (ability.getName()) {
                    case "БОЖЕСТВЕННОЕ ЧУВСТВО" ->
                        numberOfUses = Math.max(1 + CharacterCalculator.calculateAttributeModifier(character.getCharisma()), 1);
                    case "НАЛОЖЕНИЕ РУК" ->
                        numberOfUses = characterLevel * 5;
                }
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