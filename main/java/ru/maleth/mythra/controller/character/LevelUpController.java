package ru.maleth.mythra.controller.character;

import com.google.gson.Gson;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.model.CharClassLevel;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.CharClassLevelRepo;
import ru.maleth.mythra.repo.CharacterRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Data
@RequestMapping("/{name}/{charName}/levelup")
public class LevelUpController {

    private final CharacterRepo characterRepo;
    private final CharClassLevelRepo charClassLevelRepo;

    private static final String PAGE = "directToPage";

    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public String sendToLvlUp(@PathVariable("name") String userName, @PathVariable("charName") String charName, Model model) {
        Map<String, String> attributes = new HashMap<>();
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
        for (CharClassLevel charClassLevel : cclList) {
            String className = ClassEnum.valueOf(charClassLevel.getCharClass().getName()).getName();
            characterClassesWithLevels.add(className);
            characterClassesWithLevels.add(String.valueOf(charClassLevel.getClassLevel()));
        }
        Gson gson = new Gson();
        String characterClassesWithLevelsJson = gson.toJson(characterClassesWithLevels);
        attributes.put("array", characterClassesWithLevelsJson);
        attributes.put("charName", charName);
        attributes.put("size", String.valueOf(characterClassesWithLevels.size()));
        attributes.put(PAGE, "levelup");
        model.addAllAttributes(attributes);
        //System.out.println(model);
        return model.getAttribute(PAGE).toString();
    }

}
