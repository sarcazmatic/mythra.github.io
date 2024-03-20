package ru.maleth.mythra.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.maleth.mythra.dto.AbilityChargeModifierDto;
import ru.maleth.mythra.dto.NumberModifierDto;
import ru.maleth.mythra.service.sheet.CharsheetService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/charsheet")
public class SheetController {

    private final CharsheetService charsheetService;

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
}
