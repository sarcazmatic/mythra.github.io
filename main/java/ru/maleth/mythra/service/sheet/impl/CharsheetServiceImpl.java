package ru.maleth.mythra.service.sheet.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.AbilityChargeModifierDto;
import ru.maleth.mythra.dto.AbilityJsonResponseDto;
import ru.maleth.mythra.dto.AbilityJsonResponseMapper;
import ru.maleth.mythra.dto.NumberModifierDto;
import ru.maleth.mythra.enums.ClassEnum;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.service.sheet.CharsheetService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharsheetServiceImpl implements CharsheetService {

    private final CharacterRepo characterRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    @Override
    public String abilityLoader(Long charId) {
        List<AbilityJsonResponseDto> ccaList = charClassAbilityRepo.findAllByCharacter_Id(charId)
                .stream().map(AbilityJsonResponseMapper::fromCharClassAbility).toList();
        Gson gson = new Gson();
        String response = gson.toJson(ccaList);
        return response;
    }

    @Override
    public String updAbilityCharge(AbilityChargeModifierDto abilityChargeModifierDto) {
        String charClassName = ClassEnum.getClassByName(abilityChargeModifierDto.getCharClass()).toString();
        CharClassAbility cca = charClassAbilityRepo.findByCharacter_CharNameAndAbility_NameAndCharClass_Name(abilityChargeModifierDto.getCharName(), abilityChargeModifierDto.getAbilName(), charClassName);
        cca.setNumberOfUses(abilityChargeModifierDto.getModifier());
        charClassAbilityRepo.updateCharges(cca.getAbility().getName(), cca.getCharacter().getCharName(), cca.getCharClass().getName(), cca.getNumberOfUses());
        Gson gson = new Gson();
        String response = gson.toJson(cca);
        return response;
    }

    @Override
    public String updExp(NumberModifierDto numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        character.setExperience(character.getExperience() + numberModifierDto.getModifier());
        characterRepo.updateExp(character.getCharName(), character.getExperience());
        Gson gson = new Gson();
        String response = gson.toJson(character);
        return response;
    }

    @Override
    public String updHeal(NumberModifierDto numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        character.setCurrentHP(character.getCurrentHP() + numberModifierDto.getModifier());
        characterRepo.updateHP(character.getCharName(), character.getCurrentHP());
        Gson gson = new Gson();
        String response = gson.toJson(character);
        return response;
    }

    @Override
    public String updDamage(NumberModifierDto numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        character.setCurrentHP(character.getCurrentHP() - numberModifierDto.getModifier());
        characterRepo.updateHP(character.getCharName(), character.getCurrentHP());
        Gson gson = new Gson();
        String response = gson.toJson(character);
        return response;
    }

}
