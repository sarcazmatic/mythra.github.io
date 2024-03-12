package ru.maleth.mythra.service.character;

import java.util.List;
import java.util.Map;

public interface CharacterService {

    Map<String, String> goToAttributes(String charName, String charClass, String charRace, String charSubrace);

    Map<String, String> goToSkills(String charName,
                                   String charClass,
                                   String charRace,
                                   int strength,
                                   int dexterity,
                                   int constitution,
                                   int intelligence,
                                   int wisdom,
                                   int charisma);

    Map<String, String> goToSheet(String userName,
                                  String charName,
                                  String charClass,
                                  String charRace,
                                  int strength,
                                  int dexterity,
                                  int constitution,
                                  int intelligence,
                                  int wisdom,
                                  int charisma,
                                  int hitPoints,
                                  List<String> profs);

}
