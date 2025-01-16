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
import ru.maleth.mythra.service.sheet.CharsheetService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class UpdateController {

    private final CharsheetService charsheetService;
    private final CharacterRepo characterRepo;
    private final CharClassLevelRepo charClassLevelRepo;


    @GetMapping("/charAbil/{charId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public String abilityLoader(@PathVariable (name = "charId") Long charId) {
        String response = charsheetService.abilityLoader(charId);
        return response;
    }

    @PutMapping("/abilCharge")
    @ResponseStatus(HttpStatus.OK)
    public String updAbilityCharge(@RequestBody AbilityChargeModifierDto abilityChargeModifierDto) {
        String response = charsheetService.updAbilityCharge(abilityChargeModifierDto);
        return response;
    }

    @PutMapping("/calcExp")
    @ResponseStatus(HttpStatus.OK)
    public String updExp(@RequestBody NumberModifierDto numberModifierDto) {
        String response = charsheetService.updExp(numberModifierDto);
        return response;
    }

    @PutMapping("/calcHeal")
    @ResponseStatus(HttpStatus.OK)
    public String updHeal(@RequestBody NumberModifierDto numberModifierDto) {
        String response = charsheetService.updHeal(numberModifierDto);
        return response;
    }

    @PutMapping("/calcDmg")
    @ResponseStatus(HttpStatus.OK)
    public String updDamage(@RequestBody NumberModifierDto numberModifierDto) {
        String response = charsheetService.updDamage(numberModifierDto);
        return response;
    }

    @PutMapping("/levelup")
    @ResponseStatus(HttpStatus.CREATED)
    public void testMethod(Model model, @RequestBody CharClassToLevelUp charClassToLevelUp) {
        Character character =  characterRepo.findById(charClassToLevelUp.getCharId()).get();
        CharClassLevel ccl = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId())
                .stream()
                .filter(c -> c.getCharClass().getName().equals(ClassEnum.getClassByName(charClassToLevelUp.getCharClassToLevelUp()).toString()))
                .findFirst().get();
        ccl.setClassLevel(ccl.getClassLevel()+1);
        log.info("Повышаем уровень класса персонажа: {} c {} до {}", ccl.getCharClass().getName(), ccl.getClassLevel()-1, ccl.getClassLevel());
        character.setIsLevelUpReady(false);
        characterRepo.save(character);
        charClassLevelRepo.save(ccl);
    }

}
