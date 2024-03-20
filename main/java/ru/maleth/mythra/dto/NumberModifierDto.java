package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NumberModifierDto {

    private String charName;
    private Integer modifier;

}
