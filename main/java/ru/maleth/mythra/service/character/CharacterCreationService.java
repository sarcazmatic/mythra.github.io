package ru.maleth.mythra.service.character;

import ru.maleth.mythra.dto.CharacterFullDto;
import ru.maleth.mythra.dto.NewCharacterDto;
import ru.maleth.mythra.dto.NewCharacterFullDto;
import ru.maleth.mythra.model.Character;

import java.util.Map;

public interface CharacterCreationService {

    Character createCharacter(String userName, CharacterFullDto characterFullDto);

    Map<String, String> goToAttributes(NewCharacterDto newCharacterDto);

    Map<String, String> goToSkills(NewCharacterFullDto newCharacterFullDto);

    Map<String, String> formSheet(String userName, CharacterFullDto characterFullDto);

}
