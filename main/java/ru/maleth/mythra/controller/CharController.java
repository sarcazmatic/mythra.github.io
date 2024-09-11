package ru.maleth.mythra.controller;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.CharacterFullDto;
import ru.maleth.mythra.dto.NewCharacterDto;
import ru.maleth.mythra.dto.NewCharacterFullDto;
import ru.maleth.mythra.model.CharClassLevel;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.CharClassLevelRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.service.character.CharacterService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Data
@RequestMapping("/{name}/{charName}")
public class CharController {

    private final CharacterService characterService;
    private final CharacterRepo characterRepo;
    private final CharClassLevelRepo charClassLevelRepo;
    private static final String PAGE = "directToPage";

    @PostMapping("/attributes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String goToAttributes(NewCharacterDto newCharacterDto, Model model) {
        Map<String, String> attributes = characterService.goToAttributes(newCharacterDto);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

    @PostMapping("/skills")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String goToSkills(NewCharacterFullDto newCharacterFullDto, Model model) {
        Map<String, String> attributes = characterService.goToSkills(newCharacterFullDto);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

    @PostMapping("/charsheet")
    @ResponseStatus(HttpStatus.CREATED)
    public String goToSheet(@PathVariable("name") String userName, CharacterFullDto characterFullDto, Model model) {
        Character character = characterService.createCharacter(userName, characterFullDto);
        Map<String, String> attributes = characterService.goToSheet(character);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

    @GetMapping("/levelup")
    @ResponseStatus(HttpStatus.OK)
    public String sendToLvlup(@PathVariable("name") String userName, @PathVariable("charName") String charName, Model model) {
        Character character;
        try {
            character = characterRepo.findByCreator_NameAndCharName(userName, charName).get();
        } catch (RuntimeException e) {
            return "test";
        }
        List<CharClassLevel> cclList = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId());
        System.out.println(cclList);
        Map<String, String> characterClasses = new HashMap<>();
        for (CharClassLevel ccl : cclList) {
            characterClasses.put(ccl.getCharClass().getName(), ccl.getClassLevel().toString());
        }
        for (Map.Entry entry : characterClasses.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }
        model.addAllAttributes(characterClasses);
        System.out.println(model);
        return "levelup";
    }

}