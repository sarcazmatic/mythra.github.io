package ru.maleth.mythra.dto.mapper;

import ru.maleth.mythra.dto.AbilityJsonResponseDTO;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.CharRaceAbility;

public class AbilityJsonResponseMapper {

    public static AbilityJsonResponseDTO fromCharClassAbility (CharClassAbility charClassAbility) {
        return AbilityJsonResponseDTO.builder()
                .name(charClassAbility.getAbility().getName())
                .description(charClassAbility.getAbility().getDescription())
                .numberOfCharges(charClassAbility.getNumberOfUses())
                .recharge(charClassAbility.getAbility().getTypeOfRest().getName())
                .cost(charClassAbility.getAbility().getCost().getName())
                .isFromClass(true)
                .build();
    }

    public static AbilityJsonResponseDTO fromCharRaceAbility (CharRaceAbility charRaceAbility) {
        return AbilityJsonResponseDTO.builder()
                .name(charRaceAbility.getAbility().getName())
                .description(charRaceAbility.getAbility().getDescription())
                .numberOfCharges(charRaceAbility.getNumberOfUses())
                .recharge(charRaceAbility.getAbility().getTypeOfRest().getName())
                .cost(charRaceAbility.getAbility().getCost().getName())
                .isFromClass(false)
                .build();
    }

}
