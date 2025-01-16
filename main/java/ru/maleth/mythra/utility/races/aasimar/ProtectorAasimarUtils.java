package ru.maleth.mythra.utility.races.aasimar;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.RaceEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharRaceAbilityRepo;
import ru.maleth.mythra.utility.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Component
public class ProtectorAasimarUtils {

    private final AbilityRepo abilityRepo;
    private final CharRaceAbilityRepo charRaceAbilityRepo;

    public List<CharRaceAbility> formAbilities(Character character) {
        Integer level = CharacterCalculator.getLevel(character.getExperience());
        List<CharRaceAbility> craList = new ArrayList<>();
        List<Ability> abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.AASIMAR, level);
        abilities.addAll(abilityRepo.findAllByRaceLimitByLevel(character.getCharRace().getRaceEnum(), level));
        for (Ability a : abilities) {
            Optional<CharRaceAbility> craOptional = Optional.ofNullable(charRaceAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), a.getName()));
            CharRaceAbility cra;
            if (craOptional.isEmpty()) {
                cra = CharRaceAbility.builder()
                        .ability(a)
                        .race(character.getCharRace())
                        .character(character)
                        .build();
                switch (cra.getAbility().getName()) {
                    case "ИСЦЕЛЯЮЩИЕ РУКИ" -> cra.setNumberOfUses(CharacterCalculator.getLevel(character.getExperience()));
                    case "СИЯЮЩАЯ ДУША" -> cra.setNumberOfUses(1);
                    default -> cra.setNumberOfUses(0);
                }
                charRaceAbilityRepo.save(cra);
            } else {
                cra = craOptional.get();
            }
            craList.add(cra);
        }
        return craList;
    }

}
