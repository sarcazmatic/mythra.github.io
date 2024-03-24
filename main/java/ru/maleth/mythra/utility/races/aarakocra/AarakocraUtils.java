package ru.maleth.mythra.utility.races.aarakocra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharRaceAbilityRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AarakocraUtils {

    private final AbilityRepo abilityRepo;
    private final CharRaceAbilityRepo charRaceAbilityRepo;

    public List<CharRaceAbility> formAbilities(Character character) {
        List<CharRaceAbility> charRaceAbilitiesList = new ArrayList<>();
        Ability ability = new Ability();
        int numberOfUses = 0;
        Optional<Ability> abilityPresent1 = Optional.ofNullable(abilityRepo.findByName("ААРАКОКРА: ПОЛЕТ"));
        Optional<Ability> abilityPresent2 = Optional.ofNullable(abilityRepo.findByName("ААРАКОКРА: КОГТИ"));
        List<Optional<Ability>> abilitiesList = List.of(abilityPresent1, abilityPresent2);
        for (int i = 0; i < abilitiesList.size(); i++) {
            if (abilitiesList.get(i).isEmpty()) {
                switch (i) {
                    case 0 -> ability = Ability.builder()
                            .name("ААРАКОКРА: ПОЛЕТ")
                            .description("""
                                    Вы можете летать со скоростью 50 футов. Для этого вы не должны носить ни средний, ни \
                                    тяжёлый доспех.""")
                            .isActive(false)
                            .requiresRest(false)
                            .typeOfRest(RestEnum.NONE)
                            .abilitySource(AbilityEnum.RACE)
                            .cost(ActionCostEnum.BLANK)
                            .build();
                    case 1 -> ability = Ability.builder()
                            .name("ААРАКОКРА: КОГТИ")
                            .description("Вы владеете своей безоружной атакой, которая причиняет при попадании рубящий урон 1к4.")
                            .isActive(false)
                            .requiresRest(false)
                            .typeOfRest(RestEnum.NONE)
                            .abilitySource(AbilityEnum.RACE)
                            .cost(ActionCostEnum.BLANK)
                            .build();
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
