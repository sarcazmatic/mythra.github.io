package ru.maleth.mythra.service.sheet;

import org.springframework.web.bind.annotation.RequestBody;
import ru.maleth.mythra.dto.AbilityChargeModifierDto;
import ru.maleth.mythra.dto.NumberModifierDto;

public interface CharsheetService {

    String abilityLoader(Long charId);

    String updAbilityCharge(AbilityChargeModifierDto abilityChargeModifierDto);

    String updExp(NumberModifierDto numberModifierDto);

    String updHeal(NumberModifierDto numberModifierDto);

    String updDamage(NumberModifierDto numberModifierDto);

}
