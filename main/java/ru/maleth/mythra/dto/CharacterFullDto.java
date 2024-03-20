package ru.maleth.mythra.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class CharacterFullDto {

    private String charName;
    private String charClass;
    private String charRace;
    private int strength;
    private int dexterity;
    private int constitution;
    private int intelligence;
    private int wisdom;
    private int charisma;
    private int hitPoints;
    private List<String> profs;

}
