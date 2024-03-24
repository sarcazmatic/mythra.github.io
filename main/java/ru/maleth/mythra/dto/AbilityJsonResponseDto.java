package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AbilityJsonResponseDto {
    private String name;
    private String description;
    private Integer numberOfCharges;
    private String recharge;
    private String cost;
    private Boolean isFromClass;
}
