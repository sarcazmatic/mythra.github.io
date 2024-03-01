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

@Controller
@Data
public class CharController {

    private final CharacterService characterService;
    private final ProficiencyRepo proficiencyRepo;

    @PostMapping("{name}/{charName}/attributes")
    public String selectAttributes(@PathVariable(value = "charName") String charName,
                                   @RequestParam(value = "charClass") String charClass,
                                   @RequestParam(value = "charRace") String charRace,
                                   @RequestParam(value = "charSubrace", required = false) List<String> charSubrace,
                                   Model model) {
        model.addAttribute("charName", charName);
        if (charClass.contains("-")) {
            String[] charClassArray = charClass.split("-");
            String charClassOne = charClassArray[0];
            model.addAttribute("charClassOne", charClassOne);
            try {
                String charClassTwo = charClassArray[1];
                model.addAttribute("charClassTwo", " " + charClassTwo);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        } else {
            model.addAttribute("charClassOne", charClass);
        }

        if (charRace.equals("Гит")
                || charRace.equals("Гном")
                || charRace.equals("Дварф")
                || charRace.equals("Полурослик")
                || charRace.equals("Эльф")) {
            if (!charSubrace.stream().allMatch(s -> s.equals("0"))) {
                String[] charRaceArray = charSubrace.stream().filter(s -> !s.equals("0")).findFirst().get().split("-");
                String charRaceOne = charRaceArray[0];
                model.addAttribute("charRaceOne", charRaceOne);
                try {
                    String charRaceTwo = charRaceArray[1];
                    model.addAttribute("charRaceTwo", " " + charRaceTwo);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }

            }
        } else {
            model.addAttribute("charRaceOne", charRace);
        }

        return "attributes";
    }

    @PostMapping("{name}/{charName}/skills")
    public String selectSkills(@PathVariable(value = "charName") String charName,
                               @RequestParam(value = "charClass") String charClass,
                               @RequestParam(value = "charRace") String charRace,
                               @RequestParam(value = "strength") int strength,
                               @RequestParam(value = "dexterity") int dexterity,
                               @RequestParam(value = "constitution") int constitution,
                               @RequestParam(value = "intelligence") int intelligence,
                               @RequestParam(value = "wisdom") int wisdom,
                               @RequestParam(value = "charisma") int charisma,
                               Model model) {
        if (charClass.contains("-")) {
            String[] charClassArray = charClass.split("-");
            String charClassOne = charClassArray[0];
            model.addAttribute("charClassOne", charClassOne);
            try {
                String charClassTwo = charClassArray[1];
                model.addAttribute("charClassTwo", " " + charClassTwo);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        } else {
            model.addAttribute("charClassOne", charClass);
        }

        if (charRace.contains("-")) {
            String[] charRaceArray = charRace.split("-");
            String charRaceOne = charRaceArray[0];
            model.addAttribute("charRaceOne", charRaceOne);
            try {
                String charRaceTwo = charRaceArray[1];
                model.addAttribute("charRaceTwo", " " + charRaceTwo);
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        } else {
            model.addAttribute("charRaceOne", charRace);
        }

        model.addAttribute("hitPoints",
                CharacterCalculator.calculateDieHit(CharClassEnum.getClassByName(charClass)) +
                        CharacterCalculator.calculateAttributeModifier(constitution));
        model.addAttribute("charName", charName);
        model.addAttribute("strength", strength);
        model.addAttribute("dexterity", dexterity);
        model.addAttribute("constitution", constitution);
        model.addAttribute("intelligence", intelligence);
        model.addAttribute("wisdom", wisdom);
        model.addAttribute("charisma", charisma);
        model.addAttribute("strengthMod", CharacterCalculator.calculateAttributeModifier(strength));
        model.addAttribute("dexterityMod", CharacterCalculator.calculateAttributeModifier(dexterity));
        model.addAttribute("intelligenceMod", CharacterCalculator.calculateAttributeModifier(intelligence));
        model.addAttribute("wisdomMod", CharacterCalculator.calculateAttributeModifier(wisdom));
        model.addAttribute("charismaMod", CharacterCalculator.calculateAttributeModifier(charisma));
        return "skills";
    }

    @PostMapping("{name}/{charName}/charsheet")
    public String selectSkills(@PathVariable(value = "charName") String charName,
                               @RequestParam(value = "charClass") String charClass,
                               @RequestParam(value = "charRace") String charRace,
                               @RequestParam(value = "strength") int strength,
                               @RequestParam(value = "dexterity") int dexterity,
                               @RequestParam(value = "constitution") int constitution,
                               @RequestParam(value = "intelligence") int intelligence,
                               @RequestParam(value = "wisdom") int wisdom,
                               @RequestParam(value = "charisma") int charisma,
                               @RequestParam(name = "hitPoints") int hitPoints,
                               @RequestParam(name = "prof", required = false) List<String> profs,
                               Model model) {
        Character character = characterService.createNewCharacter(charName, charClass, charRace, strength, dexterity, constitution,
                intelligence, wisdom, charisma, profs, hitPoints);
        model.addAttribute("charName", character.getCharName());
        model.addAttribute("charRace", character.getCharRaceEnum().getName());
        model.addAttribute("charClass", character.getCharacterClasses().stream().findFirst().get().getName());
        model.addAttribute("ac", character.getArmorClass());
        model.addAttribute("speed", character.getSpeed());
        model.addAttribute("initiative", character.getInitiative());
        int experience = character.getExperience();
        model.addAttribute("experience", experience);
        model.addAttribute("level", CharacterCalculator.getLevel(experience));
        model.addAttribute("proficiency", CharacterCalculator.getProfBonus(experience));

        model.addAttribute("strength", strength);
        model.addAttribute("strengthmod", CharacterCalculator.calculateAttributeModifier(strength));
        model.addAttribute("dexterity", dexterity);
        model.addAttribute("dexteritymod", CharacterCalculator.calculateAttributeModifier(dexterity));
        model.addAttribute("constitution", constitution);
        model.addAttribute("constitutionmod", CharacterCalculator.calculateAttributeModifier(constitution));
        model.addAttribute("intelligence", intelligence);
        model.addAttribute("intelligencemod", CharacterCalculator.calculateAttributeModifier(intelligence));
        model.addAttribute("wisdom", wisdom);
        model.addAttribute("wisdommod", CharacterCalculator.calculateAttributeModifier(wisdom));
        model.addAttribute("charisma", charisma);
        model.addAttribute("charismamod", CharacterCalculator.calculateAttributeModifier(charisma));

        List<String> proficienciesFromEnum = Arrays.stream(CharProfEnum.values())
                .map(charProfEnum -> charProfEnum.toString().toLowerCase())
                .toList(); //тут мы собрали список скиллов (nn_nn вместо NN_NN)
        for (String s : proficienciesFromEnum) {
            if (profs.contains(s.replace('_', '-'))) {
                Proficiency proficiency = character.getProficiencies().stream().filter(p
                        -> p.getName().equals(CharProfEnum.valueOf(s.toUpperCase()).getName())).findFirst().get();
                CharAttribEnum baseAttrib = CharAttribEnum.valueOf(proficiency.getBaseAttribute().toString());
                switch (baseAttrib) {
                    case STRENGTH ->
                            model.addAttribute(CharProfEnum.getProfByName(proficiency.getName()).toString().toLowerCase(),
                                    CharacterCalculator.calculateAttributeModifier(strength) + CharacterCalculator.getProfBonus(character.getExperience()));
                    case DEXTERITY ->
                            model.addAttribute(CharProfEnum.getProfByName(proficiency.getName()).toString().toLowerCase(),
                                    CharacterCalculator.calculateAttributeModifier(dexterity) + CharacterCalculator.getProfBonus(character.getExperience()));
                    case INTELLIGENCE ->
                            model.addAttribute(CharProfEnum.getProfByName(proficiency.getName()).toString().toLowerCase(),
                                    CharacterCalculator.calculateAttributeModifier(intelligence) + CharacterCalculator.getProfBonus(character.getExperience()));
                    case WISDOM ->
                            model.addAttribute(CharProfEnum.getProfByName(proficiency.getName()).toString().toLowerCase(),
                                    CharacterCalculator.calculateAttributeModifier(wisdom) + CharacterCalculator.getProfBonus(character.getExperience()));
                    case CHARISMA ->
                            model.addAttribute(CharProfEnum.getProfByName(proficiency.getName()).toString().toLowerCase(),
                                    CharacterCalculator.calculateAttributeModifier(charisma) + CharacterCalculator.getProfBonus(character.getExperience()));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            } else {
                switch (s) {
                    case "athletics" -> model.addAttribute(s, CharacterCalculator.calculateAttributeModifier(strength));
                    case "acrobatics", "stealth", "sleight_of_hand" ->
                            model.addAttribute(s, CharacterCalculator.calculateAttributeModifier(dexterity));
                    case "arcana", "history", "investigation", "nature", "religion" ->
                            model.addAttribute(s, CharacterCalculator.calculateAttributeModifier(intelligence));
                    case "insight", "medicine", "perception", "survival", "animal_handling" ->
                            model.addAttribute(s, CharacterCalculator.calculateAttributeModifier(wisdom));
                    case "deception", "intimidation", "performance", "persuasion" ->
                            model.addAttribute(s, CharacterCalculator.calculateAttributeModifier(charisma));
                    default -> throw new RuntimeException("Тут такое вообще произошло");
                }
            }
        }

        List<CharAttribEnum> savingThrows = new ArrayList<>();
        savingThrows.add(character.getCharacterClasses().stream().findFirst().get().getSavingThrowOne());
        savingThrows.add(character.getCharacterClasses().stream().findFirst().get().getSavingThrowTwo());

        model.addAttribute("strengthsave", CharacterCalculator.calculateAttributeModifier(strength));
        model.addAttribute("dexteritysave", CharacterCalculator.calculateAttributeModifier(dexterity));
        model.addAttribute("constitutionsave", CharacterCalculator.calculateAttributeModifier(constitution));
        model.addAttribute("intelligencesave", CharacterCalculator.calculateAttributeModifier(intelligence));
        model.addAttribute("wisdomsave", CharacterCalculator.calculateAttributeModifier(wisdom));
        model.addAttribute("charismasave", CharacterCalculator.calculateAttributeModifier(charisma));

        for (int k = 0; k < savingThrows.size();k++) {
            switch (savingThrows.get(k).toString()) {
                case "STRENGTH" ->
                        model.addAttribute(savingThrows.get(k).toString().toLowerCase()+"save",
                                CharacterCalculator.calculateAttributeModifier(strength)+CharacterCalculator.getProfBonus(character.getExperience()));
                case "DEXTERITY" ->
                        model.addAttribute(savingThrows.get(k).toString().toLowerCase()+"save",
                                CharacterCalculator.calculateAttributeModifier(dexterity)+CharacterCalculator.getProfBonus(character.getExperience()));
                case "CONSTITUTION" ->
                        model.addAttribute(savingThrows.get(k).toString().toLowerCase()+"save",
                                CharacterCalculator.calculateAttributeModifier(constitution)+CharacterCalculator.getProfBonus(character.getExperience()));
                case "INTELLIGENCE" ->
                        model.addAttribute(savingThrows.get(k).toString().toLowerCase()+"save",
                                CharacterCalculator.calculateAttributeModifier(intelligence)+CharacterCalculator.getProfBonus(character.getExperience()));
                case "WISDOM" ->
                        model.addAttribute(savingThrows.get(k).toString().toLowerCase()+"save",
                                CharacterCalculator.calculateAttributeModifier(wisdom)+CharacterCalculator.getProfBonus(character.getExperience()));
                case "CHARISMA" ->
                        model.addAttribute(savingThrows.get(k).toString().toLowerCase()+"save",
                                CharacterCalculator.calculateAttributeModifier(charisma)+CharacterCalculator.getProfBonus(character.getExperience()));
                default ->
                        throw new RuntimeException("Не получилось проставить спасброски");
            }
        }

        model.addAttribute("hitPoints", hitPoints);
        return "charsheet2";
    }

}
