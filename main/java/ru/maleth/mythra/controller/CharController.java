package ru.maleth.mythra.controller;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.CharClassToLevelUp;
import ru.maleth.mythra.dto.CharacterFullDto;
import ru.maleth.mythra.dto.NewCharacterDto;
import ru.maleth.mythra.dto.NewCharacterFullDto;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.model.CharClassLevel;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.CharClassLevelRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.service.character.CharacterService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.*;

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

    @GetMapping("/charsheet")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String updSheet(@PathVariable("name") String userName, @PathVariable("charName") String charName, Model model) {
        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        Character character = characterRepo.findByCreator_NameAndCharName(userName, charName).get();
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
        //System.out.println(cclList);
        List<String> characterClassesWithLevels = new ArrayList<>();
        //for (CharClassLevel ccl : cclList) {
        for (int i = 0; i < cclList.size(); i++) {
            String className = ClassEnum.valueOf(cclList.get(i).getCharClass().getName()).getName();
            characterClassesWithLevels.add(className);
            characterClassesWithLevels.add(String.valueOf(cclList.get(i).getClassLevel()));
        }
        Gson gson = new Gson();
        String testUpd = gson.toJson(characterClassesWithLevels);
        model.addAttribute("array", testUpd);
        model.addAttribute("charName", charName);
        model.addAttribute("size", characterClassesWithLevels.size());
        //System.out.println(model);
        return "levelup";
    }

    @PutMapping("/charsheet/updCharacterLevel")
    @ResponseStatus(HttpStatus.CREATED)
    public void testMethod(Model model, @PathVariable String name, @PathVariable String charName, @RequestBody CharClassToLevelUp charClassToLevelUp) {
        //System.out.println("ББББ");
        //System.out.println(charClassToLevelUp.getCharClassToLevelUp());
        String charClassToLevelUpName = ClassEnum.getClassByName(charClassToLevelUp.getCharClassToLevelUp()).toString();
        //System.out.println(charClassToLevelUpName);
        charClassToLevelUp.setCharName(charName);
        //System.out.println(charClassToLevelUp.getCharName());
        Character character =  characterRepo.findByCreator_NameAndCharName(name, charName).get();
        //System.out.println(character);
        CharClassLevel ccl = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId()).stream().filter(c -> c.getCharClass().getName().equals(ClassEnum.getClassByName(charClassToLevelUp.getCharClassToLevelUp()).toString())).findFirst().get();
        //System.out.println(ccl);
        ccl.setClassLevel(ccl.getClassLevel()+1);
        character.setIsLevelUpReady(false);
        characterRepo.save(character);
        charClassLevelRepo.save(ccl);
        //System.out.println(ccl);

    }

}