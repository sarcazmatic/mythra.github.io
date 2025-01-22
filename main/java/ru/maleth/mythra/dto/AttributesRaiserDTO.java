package ru.maleth.mythra.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class AttributesRaiserDTO {

    private Long charId;
    private String charName;
    private List<String> attrsArray;

}
