package ru.maleth.mythra.service.sheet;

import ru.maleth.mythra.dto.AbilityChargeModifierDto;
import ru.maleth.mythra.dto.NumberModifierDto;
import ru.maleth.mythra.model.Character;

import java.util.Map;

public interface CharsheetService {

    Map<String, String> getSheet(Character character);

    String abilityLoader(Long charId);

    String updAbilityCharge(AbilityChargeModifierDto abilityChargeModifierDto);

    String updExp(NumberModifierDto numberModifierDto);

    String updHeal(NumberModifierDto numberModifierDto);

    String updDamage(NumberModifierDto numberModifierDto);

}
