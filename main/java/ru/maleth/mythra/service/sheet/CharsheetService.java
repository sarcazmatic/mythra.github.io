package ru.maleth.mythra.service.sheet;

import ru.maleth.mythra.dto.AbilityChargeModifierDto;
import ru.maleth.mythra.dto.NumberModifierDto;

public interface CharsheetService {

    String updAbilityCharge(AbilityChargeModifierDto abilityChargeModifierDto);

    String updExp(NumberModifierDto numberModifierDto);

    String updHeal(NumberModifierDto numberModifierDto);

    String updDamage(NumberModifierDto numberModifierDto);

}
