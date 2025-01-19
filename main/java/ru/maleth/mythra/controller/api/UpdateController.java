package ru.maleth.mythra.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.AbilityChargeModifierDto;
import ru.maleth.mythra.dto.CharClassToLevelUp;
import ru.maleth.mythra.dto.NumberModifierDto;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.model.CharClassLevel;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.CharClassLevelRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.service.levelup.LevelUpService;
import ru.maleth.mythra.service.sheet.CharsheetService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UpdateController {

    private final CharsheetService charsheetService;
    private final LevelUpService levelUpService;


    @GetMapping("/charAbil/{charId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String abilityLoader(@PathVariable(name = "charId") Long charId) {
        log.info("Пришел запрос на загрузку абилок для персонажа с id {}", charId);
        String response = charsheetService.abilityLoader(charId);
        return response;
    }

    @PutMapping("/abilCharge")
    @ResponseStatus(HttpStatus.OK)
    public String updAbilityCharge(@RequestBody AbilityChargeModifierDto abilityChargeModifierDto) {
        log.info("Пришел запрос на обновление кол-ва использований персонажем с id {} абилки '{}'. Новое значение: {} единиц",
                abilityChargeModifierDto.getAbilName(),
                abilityChargeModifierDto.getCharId(),
                abilityChargeModifierDto.getModifier());
        String response = charsheetService.updAbilityCharge(abilityChargeModifierDto);
        return response;
    }

    @PutMapping("/calcExp")
    @ResponseStatus(HttpStatus.OK)
    public String updExp(@RequestBody NumberModifierDto numberModifierDto) {
        log.info("Пришел запрос на изменение опыта для персонажа {} на {}",
                numberModifierDto.getCharName(),
                numberModifierDto.getModifier());
        String response = charsheetService.updExp(numberModifierDto);
        return response;
    }

    @PutMapping("/calcHeal")
    @ResponseStatus(HttpStatus.OK)
    public String updHeal(@RequestBody NumberModifierDto numberModifierDto) {
        log.info("Пришел запрос на лечение для персонажа {} на {} hp",
                numberModifierDto.getCharName(),
                numberModifierDto.getModifier());
        String response = charsheetService.updHeal(numberModifierDto);
        return response;
    }

    @PutMapping("/calcDmg")
    @ResponseStatus(HttpStatus.OK)
    public String updDamage(@RequestBody NumberModifierDto numberModifierDto) {
        log.info("Пришел запрос на урон по персонажу {} на {} hp",
                numberModifierDto.getCharName(),
                numberModifierDto.getModifier());
        String response = charsheetService.updDamage(numberModifierDto);
        return response;
    }

    @PutMapping("/levelup")
    @ResponseStatus(HttpStatus.CREATED)
    public void levelUp(Model model, @RequestBody CharClassToLevelUp charClassToLevelUp) {
        log.info("Пришел запрос на повышение уровня персонажа с id {} для его класса {}",
                charClassToLevelUp.getCharId(),
                charClassToLevelUp.getCharClassToLevelUp());
        levelUpService.levelUp(charClassToLevelUp);
    }

}
