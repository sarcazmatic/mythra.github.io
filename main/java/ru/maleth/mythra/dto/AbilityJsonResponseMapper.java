package ru.maleth.mythra.dto;

import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.User;

public class AbilityJsonResponseMapper {

    public static AbilityJsonResponseDto fromCharClassAbility (CharClassAbility charClassAbility) {
        return AbilityJsonResponseDto.builder()
                .name(charClassAbility.getAbility().getName())
                .description(charClassAbility.getAbility().getDescription())
                .numberOfCharges(charClassAbility.getNumberOfUses())
                .recharge(charClassAbility.getAbility().getTypeOfRest().getName())
                .cost(charClassAbility.getAbility().getCost().getName())
                .build();
    }

}
