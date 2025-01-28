package ru.maleth.mythra.service.sheet;

import ru.maleth.mythra.dto.AbilityChargeModifierDTO;
import ru.maleth.mythra.dto.AttributesRaiserDTO;
import ru.maleth.mythra.dto.NumberModifierDTO;

import java.util.Map;

public interface CharsheetService {

    Map<String, String> getSheet(String userName, String charName);

    String abilityLoader(Long charId);

    String updAbilityCharge(AbilityChargeModifierDTO abilityChargeModifierDto);

    String updExp(NumberModifierDTO numberModifierDto);

    String updHeal(NumberModifierDTO numberModifierDto);

    String updDamage(NumberModifierDTO numberModifierDto);

    void raiseAttributes(AttributesRaiserDTO attributesRaiserDTO);

}
