package ru.maleth.mythra.controller;

import lombok.Data;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.Proficiency;
import ru.maleth.mythra.repo.ProficiencyRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.character.enums.CharAttribEnum;
import ru.maleth.mythra.service.character.enums.CharClassEnum;
import ru.maleth.mythra.service.character.enums.CharProfEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Controller
@Data
public class CharController {

    private final CharacterService characterService;

    @PostMapping("{name}/{charName}/attributes")
    public String goToAttributes(@PathVariable("charName") String charName,
                                 @RequestParam(value = "charClass") String charClass,
                                 @RequestParam(value = "charRace") String charRace,
                                 @RequestParam(value = "charSubrace", required = false) String charSubrace,
                                 Model model) {
        Map<String, String> attributes = characterService.goToAttributes(charName, charClass, charRace, charSubrace);
        model.addAllAttributes(attributes);
        return attributes.get("directToPage");
    }

    @PostMapping("{name}/{charName}/skills")
    public String goToSkills(@PathVariable("charName") String charName,
                             @RequestParam(value = "charClass") String charClass,
                             @RequestParam(value = "charRace") String charRace,
                             @RequestParam(value = "strength") int strength,
                             @RequestParam(value = "dexterity") int dexterity,
                             @RequestParam(value = "constitution") int constitution,
                             @RequestParam(value = "intelligence") int intelligence,
                             @RequestParam(value = "wisdom") int wisdom,
                             @RequestParam(value = "charisma") int charisma,
                             Model model) {
        Map<String, String> attributes = characterService.goToSkills(charName, charClass, charRace,
                strength, dexterity, constitution,
                intelligence, wisdom, charisma);
        model.addAllAttributes(attributes);
        return attributes.get("directToPage");
    }

    @PostMapping("{name}/{charName}/charsheet")
    public String goToSheet(@PathVariable("charName") String charName,
                               @RequestParam(value = "charClass") String charClass,
                               @RequestParam(value = "charRace") String charRace,
                               @RequestParam(value = "strength") int strength,
                               @RequestParam(value = "dexterity") int dexterity,
                               @RequestParam(value = "constitution") int constitution,
                               @RequestParam(value = "intelligence") int intelligence,
                               @RequestParam(value = "wisdom") int wisdom,
                               @RequestParam(value = "charisma") int charisma,
                               @RequestParam(name = "hitPoints") int hitPoints,
                               @RequestParam(name = "prof") List<String> profs,
                               Model model) {
        Map<String, String> attributes = characterService.goToSheet(charName, charClass, charRace, strength, dexterity, constitution, intelligence, wisdom, charisma, hitPoints, profs);
        model.addAllAttributes(attributes);
        return attributes.get("directToPage");    }

}
