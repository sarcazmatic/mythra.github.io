package ru.maleth.mythra.service.character;

import ru.maleth.mythra.dto.CharacterFullDTO;
import ru.maleth.mythra.dto.NewCharacterDTO;
import ru.maleth.mythra.dto.NewCharacterFullDTO;
import ru.maleth.mythra.model.Character;

import java.util.Map;

public interface CharacterCreationService {

    Character createCharacter(String userName, CharacterFullDTO characterFullDto);

    Map<String, String> goToAttributes(NewCharacterDTO newCharacterDto);

    Map<String, String> goToSkills(NewCharacterFullDTO newCharacterFullDto);

    Map<String, String> formSheet(String userName, CharacterFullDTO characterFullDto);

}
