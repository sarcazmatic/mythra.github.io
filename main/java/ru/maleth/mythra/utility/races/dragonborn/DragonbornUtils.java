package ru.maleth.mythra.utility.races.dragonborn;

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

@Component
@RequiredArgsConstructor
public class DragonbornUtils {

    private final AbilityRepo abilityRepo;
    private final CharRaceAbilityRepo charRaceAbilityRepo;

    public List<CharRaceAbility> formAbilities(Character character) {
        Integer level = CharacterCalculator.getLevel(character.getExperience());
        List<CharRaceAbility> craList = new ArrayList<>();
        List<Ability> abilities = new ArrayList<>();
        switch (character.getCharRace().getRaceEnum()) {
            case WHITE_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.WHITE_DRAGONBORN, level);
            case BRONZE_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.BRONZE_DRAGONBORN, level);
            case GOLD_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.GOLD_DRAGONBORN, level);
            case GREEN_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.GREEN_DRAGONBORN, level);
            case RED_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.RED_DRAGONBORN, level);
            case BRASS_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.BRASS_DRAGONBORN, level);
            case COPPER_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.COPPER_DRAGONBORN, level);
            case SILVER_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.SILVER_DRAGONBORN, level);
            case BLUE_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.BLUE_DRAGONBORN, level);
            case BLACK_DRAGONBORN ->
                    abilities = abilityRepo.findAllByRaceLimitByLevel(RaceEnum.BLACK_DRAGONBORN, level);
        }
        for (Ability a : abilities) {
            Optional<CharRaceAbility> craOptional = Optional.ofNullable(charRaceAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), a.getName()));
            CharRaceAbility cra;
            if (craOptional.isEmpty()) {
                cra = CharRaceAbility.builder()
                        .ability(a)
                        .race(character.getCharRace())
                        .character(character)
                        .build();
                if ("ОРУЖИЕ ДЫХАНИЯ".equals(cra.getAbility().getName())) {
                    cra.setNumberOfUses(1);
                } else {
                    cra.setNumberOfUses(0);
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

