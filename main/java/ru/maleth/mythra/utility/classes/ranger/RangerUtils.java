package ru.maleth.mythra.utility.classes.ranger;

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
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RangerUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(Character character, CharClass charClass) {
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        int numberOfUses = 0;
        List<Optional<Ability>> optionalAbilities = new ArrayList<>();
        Optional<Ability> abilityNoArmorOpt = Optional.ofNullable(abilityRepo.findByName("ИЗБРАННЫЙ ВРАГ"));
        Optional<Ability> abilityArtOfFight = Optional.ofNullable(abilityRepo.findByName("ИССЛЕДОВАТЕЛЬ ПРИРОДЫ"));
        optionalAbilities.add(abilityNoArmorOpt);
        optionalAbilities.add(abilityArtOfFight);
        for (int i = 0; i < optionalAbilities.size(); i++) {
            Ability ability = new Ability();
            if (optionalAbilities.get(i).isEmpty()) {
                switch (i) {
                    case 0 -> {
                        ability = Ability.builder()
                                .name("ИЗБРАННЫЙ ВРАГ")
                                .description("""
                                        Начиная с 1 уровня у вас есть значительный опыт изучения, отслеживания, охоты и \
                                        даже общения с определённым видом врагов.
                                        Выберите вид избранного врага: аберрации, великаны, драконы, звери, исчадия, \
                                        конструкты, монстры, небожители, нежить, растения, слизи, феи или элементали. В \
                                        качестве альтернативы вы можете выбрать в качестве избранных врагов две \
                                        гуманоидные расы (например, гноллов и орков).
                                        Вы совершаете с преимуществом проверки Мудрости (Выживание) для выслеживания \
                                        избранных врагов, а также проверки Интеллекта для вспоминания информации о них.
                                        Когда вы получаете это умение, вы также обучаетесь одному из языков, на котором \
                                        говорит ваш избранный враг, если он вообще умеет говорить.
                                        Вы дополнительно выбираете по одному избранному врагу и языку, связанному с \
                                        ним, на 6 и 14 уровнях. Получая уровни, ваш выбор должен отражать чудовищ, с \
                                        которыми вы уже сталкивались во время приключений.""")
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
                                .name("ИССЛЕДОВАТЕЛЬ ПРИРОДЫ")
                                .description("""
                                        Вы очень хорошо знакомы с одним видом природной среды и имеете большой опыт \
                                        путешествий и выживания в регионах с таким климатом. Выберите один вид известной \
                                        местности: арктика, болота, горы, леса, луга, побережье, Подземье или пустыня. \
                                        Когда вы совершаете проверку Интеллекта или Мудрости, связанную с известной вам \
                                        местностью, ваш бонус мастерства удваивается, если вы используете навык, которым владеете.
                                        Путешествуя по избранной вами местности в течении часа или более, вы получаете \
                                        следующие преимущества:
                                        • Труднопроходимая местность не замедляет путешествие группы.
                                        • Ваша группа не может заблудиться, кроме как по вине магии.
                                        • Даже когда вы занимаетесь другой деятельностью во время путешествия \
                                        (например, ищете пищу, ориентируетесь или выслеживание), вы остаётесь готовы к опасности.
                                        • Если вы путешествуете в одиночку, вы можете перемещаться скрытно в нормальном темпе.
                                        • Когда вы ищете пищу, то находите в два раза больше, чем обычно.
                                        • Когда вы выслеживаете других существ, вы также узнаёте их точное количество, \
                                        их размеры, и как давно они прошли через область.
                                        Вы можете выбрать дополнительную известную местность на 6 и 10 уровнях.""")
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
