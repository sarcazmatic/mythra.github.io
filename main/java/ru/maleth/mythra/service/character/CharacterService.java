package ru.maleth.mythra.service.character;

import ru.maleth.mythra.model.Character;

import java.util.List;

public interface CharacterService {

    Character createNewCharacter(String charName,
                                 String charClass,
                                 String charRace,
                                 int strength,
                                 int dexterity,
                                 int constitution,
                                 int intelligence,
                                 int wisdom,
                                 int charisma,
                                 List<String> profs,
                                 int hitPoints);

}
