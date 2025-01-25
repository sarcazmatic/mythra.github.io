package ru.maleth.mythra.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.AbilityChargeModifierDTO;
import ru.maleth.mythra.dto.AttributesRaiserDTO;
import ru.maleth.mythra.dto.CharClassToLevelUpDTO;
import ru.maleth.mythra.dto.NumberModifierDTO;
import ru.maleth.mythra.service.character.CharacterService;
import ru.maleth.mythra.service.levelup.LevelUpService;
import ru.maleth.mythra.service.sheet.CharsheetService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UpdateController {

    private final CharsheetService charsheetService;
    private final CharacterService characterService;

    private final LevelUpService levelUpService;


    @GetMapping("/charAbil/{charId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String abilityLoader(@PathVariable(name = "charId") Long charId) {
        log.info("Пришел запрос на загрузку абилок для персонажа с id {}", charId);
        String response = charsheetService.abilityLoader(charId);
        return response;
    }

    @GetMapping("/attrsAndSkills/{charId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String attrsAndSkillsLoader(@PathVariable(name = "charId") Long charId) {
        log.info("Пришел запрос на загрузку атрибутов и навыков для персонажа с id {}", charId);
        String response = characterService.loadAttrsAndSkills(charId);
        return response;
        //return response;
    }

    @PutMapping("/abilCharge")
    @ResponseStatus(HttpStatus.OK)
    public String updAbilityCharge(@RequestBody AbilityChargeModifierDTO abilityChargeModifierDto) {
        log.info("Пришел запрос на обновление кол-ва использований персонажем с id {} абилки '{}'. Новое значение: {} единиц",
                abilityChargeModifierDto.getCharId(),
                abilityChargeModifierDto.getAbilName(),
                abilityChargeModifierDto.getModifier());
        String response = charsheetService.updAbilityCharge(abilityChargeModifierDto);
        return response;
    }

    @PutMapping("/updateAttributes")
    @ResponseStatus(HttpStatus.OK)
    public void updateAttributes(@RequestBody AttributesRaiserDTO attributesRaiserDTO) {
        log.info("Пришел запрос на повышение характеристик персонажа с id {} '{}'. Меняем: {}",
                attributesRaiserDTO.getCharId(),
                attributesRaiserDTO.getCharName(),
                attributesRaiserDTO.getAttrsArray());
        charsheetService.raiseAttributes(attributesRaiserDTO);
    }

    @PutMapping("/calcExp")
    @ResponseStatus(HttpStatus.OK)
    public String updExp(@RequestBody NumberModifierDTO numberModifierDto) {
        log.info("Пришел запрос на изменение опыта для персонажа {} c id {} на {}",
                numberModifierDto.getCharName(),
                numberModifierDto.getCharId(),
                numberModifierDto.getModifier());
        String response = charsheetService.updExp(numberModifierDto);
        return response;
    }

    @PutMapping("/calcHeal")
    @ResponseStatus(HttpStatus.OK)
    public String updHeal(@RequestBody NumberModifierDTO numberModifierDto) {
        log.info("Пришел запрос на лечение для персонажа {} на {} hp",
                numberModifierDto.getCharName(),
                numberModifierDto.getModifier());
        String response = charsheetService.updHeal(numberModifierDto);
        return response;
    }

    @PutMapping("/calcDmg")
    @ResponseStatus(HttpStatus.OK)
    public String updDamage(@RequestBody NumberModifierDTO numberModifierDto) {
        log.info("Пришел запрос на урон по персонажу {} на {} hp",
                numberModifierDto.getCharName(),
                numberModifierDto.getModifier());
        String response = charsheetService.updDamage(numberModifierDto);
        return response;
    }

    @PutMapping("/levelup")
    @ResponseStatus(HttpStatus.CREATED)
    public void levelUp(@RequestBody CharClassToLevelUpDTO charClassToLevelUp) {
        log.info("Пришел запрос на повышение уровня персонажа с id {} для его класса {}",
                charClassToLevelUp.getCharId(),
                charClassToLevelUp.getCharClassToLevelUp());
        levelUpService.levelUp(charClassToLevelUp);
    }

    @PostMapping("/multiclass")
    @ResponseStatus(HttpStatus.CREATED)
    public void multiclass(@RequestBody CharClassToLevelUpDTO charClassToLevelUp) {
        log.info("Пришел запрос на мультиклассирование персонажа с id {} в класс {}",
                charClassToLevelUp.getCharId(),
                charClassToLevelUp.getCharClassToLevelUp());
        levelUpService.multiClass(charClassToLevelUp);
    }

}
