package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class AbilityChargeModifierDto {

    private String charName;
    private String abilName;
    private String charClass;
    private Integer modifier;

}
