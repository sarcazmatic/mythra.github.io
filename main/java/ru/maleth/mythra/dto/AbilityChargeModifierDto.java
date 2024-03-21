package ru.maleth.mythra.dto;

import lombok.*;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AbilityChargeModifierDto {

    private String charName;
    private String abilName;
    private String charClass;
    private Integer modifier;

}
