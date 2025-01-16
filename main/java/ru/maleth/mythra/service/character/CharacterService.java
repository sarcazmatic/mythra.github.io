package ru.maleth.mythra.service.character;

import ru.maleth.mythra.model.Character;

public interface CharacterService {

    Character findByUserNameAndCharName(String userName, String charName);

}
