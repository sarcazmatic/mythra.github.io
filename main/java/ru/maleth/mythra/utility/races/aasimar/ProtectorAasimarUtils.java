package ru.maleth.mythra.utility.races.aasimar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharRaceAbilityRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProtectorAasimarUtils {

    private final AbilityRepo abilityRepo;
    private final CharRaceAbilityRepo charRaceAbilityRepo;

    public List<CharRaceAbility> formAbilities(Character character) {
        List<CharRaceAbility> charRaceAbilitiesList = new ArrayList<>();
        Ability ability = new Ability();
        int numberOfUses = 0;
        Optional<Ability> abilityPresent1 = Optional.ofNullable(abilityRepo.findByName("НЕБЕСНОЕ СОПРОТИВЛЕНИЕ"));
        Optional<Ability> abilityPresent2 = Optional.ofNullable(abilityRepo.findByName("ИСЦЕЛЯЮЩИЕ РУКИ"));
        Optional<Ability> abilityPresent3 = Optional.ofNullable(abilityRepo.findByName("НЕСУЩИЙ СВЕТ"));
        Optional<Ability> abilityPresent4 = Optional.ofNullable(abilityRepo.findByName("СИЯЮЩАЯ ДУША"));
        List<Optional<Ability>> abilitiesList = List.of(abilityPresent1, abilityPresent2, abilityPresent3, abilityPresent4);
        for (int i = 0; i < abilitiesList.size(); i++) {
            if (abilitiesList.get(i).isEmpty()) {
                switch (i) {
                    case 0 -> ability = Ability.builder()
                            .name("НЕБЕСНОЕ СОПРОТИВЛЕНИЕ")
                            .description("У вас есть сопротивление урону излучением и некротической энергией.")
                            .isActive(false)
                            .requiresRest(false)
                            .typeOfRest(RestEnum.NONE)
                            .abilitySource(AbilityEnum.RACE)
                            .cost(ActionCostEnum.BLANK)
                            .build();
                    case 1 -> {
                        ability = Ability.builder()
                                .name("ИСЦЕЛЯЮЩИЕ РУКИ")
                                .description("""
                                        Действием вы можете коснуться существа и восстановить ему количество хитов, \
                                        равное вашему уровню. Вы не сможете вновь воспользоваться этой способностью \
                                        пока не закончите продолжительный отдых.""")
                                .isActive(true)
                                .requiresRest(true)
                                .typeOfRest(RestEnum.LONG)
                                .abilitySource(AbilityEnum.RACE)
                                .cost(ActionCostEnum.ACTION)
                                .build();
                        numberOfUses = CharacterCalculator.getLevel(character.getExperience());
                    }
                    case 2 -> ability = Ability.builder()
                            .name("НЕСУЩИЙ СВЕТ")
                            .description("Вам известен заговор свет. Базовой характеристикой для его использования является Харизма.")
                            .isActive(false)
                            .requiresRest(false)
                            .typeOfRest(RestEnum.NONE)
                            .abilitySource(AbilityEnum.RACE)
                            .cost(ActionCostEnum.BLANK)
                            .build();
                    case 3 -> {
                        if (CharacterCalculator.getLevel(character.getExperience()) >= 3) {
                            ability = Ability.builder()
                                    .name("СИЯЮЩАЯ ДУША")
                                    .description("""
                                            Вы можете действием высвободить божественную энергию внутри себя, заставляя \
                                            ваши глаза мерцать, а два светящихся, бестелесных крыла вырасти у вас за \
                                            спиной. Ваше превращение длится 1 минуту или пока вы не окончите его \
                                            бонусным действием.
                                            Во время превращения вы получаете скорость полёта 30 футов и раз в свой ход \
                                            вы можете нанести дополнительный урон излучением одной цели, когда вы наносите \
                                            ей урон атакой или заклинанием. Дополнительный урон излучением равен вашему уровню.
                                            После того, как вы используете эту особенность, вы не можете использовать её \
                                            снова, пока не закончите продолжительный отдых.""")
                                    .isActive(true)
                                    .requiresRest(true)
                                    .typeOfRest(RestEnum.LONG)
                                    .abilitySource(AbilityEnum.RACE)
                                    .cost(ActionCostEnum.ACTION)
                                    .build();
                            numberOfUses = 1;
                        }
                    }
                }
                abilityRepo.save(ability);
            } else {
                ability = abilitiesList.get(i).get();
            }
            Optional<CharRaceAbility> craOptional = Optional.ofNullable(charRaceAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), ability.getName()));
            CharRaceAbility charRaceAbility;
            if (craOptional.isEmpty()) {
                charRaceAbility = CharRaceAbility.builder()
                        .character(character)
                        .ability(ability)
                        .race(character.getCharRace())
                        .numberOfUses(numberOfUses)
                        .build();
                charRaceAbilityRepo.save(charRaceAbility);
            } else {
                charRaceAbility = craOptional.get();
            }
            charRaceAbilitiesList.add(charRaceAbility);
        }
        return charRaceAbilitiesList;
    }

}
