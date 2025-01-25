package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class NumberModifierDTO {

    private Long charId;
    private String charName;
    private Integer modifier;

}
