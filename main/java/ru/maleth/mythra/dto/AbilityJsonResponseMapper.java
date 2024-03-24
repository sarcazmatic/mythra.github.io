package ru.maleth.mythra.dto;

import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.User;

public class AbilityJsonResponseMapper {

    public static AbilityJsonResponseDto fromCharClassAbility (CharClassAbility charClassAbility) {
        return AbilityJsonResponseDto.builder()
                .name(charClassAbility.getAbility().getName())
                .description(charClassAbility.getAbility().getDescription())
                .numberOfCharges(charClassAbility.getNumberOfUses())
                .recharge(charClassAbility.getAbility().getTypeOfRest().getName())
                .cost(charClassAbility.getAbility().getCost().getName())
                .isFromClass(true)
                .build();
    }

    public static AbilityJsonResponseDto fromCharRaceAbility (CharRaceAbility charRaceAbility) {
        return AbilityJsonResponseDto.builder()
                .name(charRaceAbility.getAbility().getName())
                .description(charRaceAbility.getAbility().getDescription())
                .numberOfCharges(charRaceAbility.getNumberOfUses())
                .recharge(charRaceAbility.getAbility().getTypeOfRest().getName())
                .cost(charRaceAbility.getAbility().getCost().getName())
                .isFromClass(false)
                .build();
    }

}
