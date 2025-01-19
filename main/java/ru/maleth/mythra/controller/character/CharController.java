package ru.maleth.mythra.controller.character;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.runtime.internal.cflowstack.ThreadStack;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.CharacterFullDto;
import ru.maleth.mythra.dto.NewCharacterDto;
import ru.maleth.mythra.dto.NewCharacterFullDto;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.service.character.CharacterCreationService;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.levelup.LevelUpService;
import ru.maleth.mythra.service.sheet.CharsheetService;

import java.util.*;
import java.util.concurrent.*;

@Controller
@Data
@Slf4j
@RequestMapping("/{name}/{charName}")
public class CharController {

    private final CharacterService characterService;
    private final CharacterCreationService characterCreationService;
    private final CharsheetService charsheetService;
    private final LevelUpService levelUpService;
    private static final String PAGE = "directToPage";

    @PostMapping("/attributes")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String setAttributes(NewCharacterDto newCharacterDto, Model model) {
        log.info("Пришел запрос на внесение значений атрибутов для персонажа {}, главный класс {}, раса {}",
                newCharacterDto.getCharName(),
                newCharacterDto.getCharClass(),
                newCharacterDto.getCharRace());
        Map<String, String> attributes = characterCreationService.goToAttributes(newCharacterDto);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

    @PostMapping("/skills")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String setSkills(NewCharacterFullDto newCharacterFullDto, Model model) {
        log.info("Пришел запрос на внесение значений навыков для персонажа {}, главный класс {}, раса {}",
                newCharacterFullDto.getCharName(),
                newCharacterFullDto.getCharClass(),
                newCharacterFullDto.getCharRace());
        Map<String, String> attributes = characterCreationService.goToSkills(newCharacterFullDto);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

    @PostMapping("/charsheet")
    @ResponseStatus(HttpStatus.CREATED)
    //first time loading charsheet
    public String formSheet(@PathVariable("name") String userName, CharacterFullDto characterFullDto, Model model) {
        log.info("Пришел запрос на формирование чаршита для персонажа {}, главный класс {}, раса {}",
                characterFullDto.getCharName(),
                characterFullDto.getCharClass(),
                characterFullDto.getCharRace());
        Character character = characterCreationService.createCharacter(userName, characterFullDto);
        Map<String, String> attributes = characterCreationService.formSheet(character);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }

    @GetMapping("/charsheet")
    @ResponseStatus(HttpStatus.ACCEPTED)
    //loading charsheet for >1 time
    public String getSheet(@PathVariable("name") String userName, @PathVariable("charName") String charName, Model model) {
        log.info("Пришел запрос на загрузку чаршита для персонажа {}", charName);
        Character character = characterService.findByUserNameAndCharName(userName, charName);
        Callable<String> pageAttributesGetter = () -> {
            Map<String, String> attributes = charsheetService.getSheet(character);
            model.addAllAttributes(attributes);
            return attributes.get(PAGE);
        };
        FutureTask<String> futureTask = new FutureTask<>(pageAttributesGetter);
        Thread th1 = new Thread(futureTask);
        th1.setName("abilityUpdaterThread");
        th1.start();
        try {
            th1.join();
            return futureTask.get();
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/levelup")
    @ResponseStatus(HttpStatus.OK)
    public String getLvlUpPage(@PathVariable("name") String userName, @PathVariable("charName") String charName, Model model) {
        Map<String, String> attributes = levelUpService.formLvlUpPage(userName, charName);
        model.addAllAttributes(attributes);
        return attributes.get(PAGE);
    }



}