package ru.maleth.mythra.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.CharacterFullDto;
import ru.maleth.mythra.dto.NewCharacterDto;
import ru.maleth.mythra.dto.NewCharacterFullDto;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.service.character.CharacterService;

import java.util.Map;

@Controller
@Data
public class CharController {

    private final CharacterService characterService;
    private static final String PAGE = "directToPAge";

    @PostMapping("{name}/{charName}/attributes")
    public String goToAttributes(NewCharacterDto newCharacterDto, Model model) {
        Map<String, String> attributes = characterService.goToAttributes(newCharacterDto);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

    @PostMapping("{name}/{charName}/skills")
    public String goToSkills(NewCharacterFullDto newCharacterFullDto, Model model) {
        Map<String, String> attributes = characterService.goToSkills(newCharacterFullDto);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

    @PostMapping("{name}/{charName}/charsheet")
    @ResponseStatus(HttpStatus.CREATED)
    public String goToSheet(@PathVariable("name") String userName, CharacterFullDto characterFullDto, Model model) {
        Character character = characterService.createCharacter(userName, characterFullDto);
        Map<String, String> attributes = characterService.goToSheet(character);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

}