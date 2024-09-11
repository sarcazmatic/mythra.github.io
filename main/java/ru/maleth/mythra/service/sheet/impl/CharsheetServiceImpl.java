package ru.maleth.mythra.service.sheet.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.maleth.mythra.dto.AbilityChargeModifierDto;
import ru.maleth.mythra.dto.AbilityJsonResponseDto;
import ru.maleth.mythra.dto.AbilityJsonResponseMapper;
import ru.maleth.mythra.dto.NumberModifierDto;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.Race;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.repo.CharRaceAbilityRepo;
import ru.maleth.mythra.repo.CharacterRepo;
import ru.maleth.mythra.repo.RaceRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;
import ru.maleth.mythra.service.sheet.CharsheetService;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CharsheetServiceImpl implements CharsheetService {

    private final CharacterRepo characterRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;
    private final CharRaceAbilityRepo charRaceAbilityRepo;
    private final RaceRepo raceRepo;

    @Override
    public String abilityLoader(Long charId) {
        Character character = characterRepo.findById(charId).get();
        List<AbilityJsonResponseDto> ccaList = charClassAbilityRepo.findAllByCharacter_IdOrderByAbilityAsc(charId)
                .stream().map(AbilityJsonResponseMapper::fromCharClassAbility).toList();
        List<AbilityJsonResponseDto> craList = charRaceAbilityRepo.findAllByCharacter_IdAndRaceLimitByLevelOrderByAbility_Name(character, character.getCharRace(), CharacterCalculator.getLevel(character.getExperience()))
                .stream().map(AbilityJsonResponseMapper::fromCharRaceAbility).toList();
        List<AbilityJsonResponseDto> abilList = new ArrayList<>();
        abilList.addAll(ccaList);
        abilList.addAll(craList);
        Gson gson = new Gson();
        String response = gson.toJson(abilList);
        return response;
    }

    @Override
    public String updAbilityCharge(AbilityChargeModifierDto abilityChargeModifierDto) {
        if (abilityChargeModifierDto.getIsFromClass()) {
            CharClassAbility cca = charClassAbilityRepo.findByCharacter_IdAndAbility_Name(abilityChargeModifierDto.getCharId(), abilityChargeModifierDto.getAbilName());
            cca.setNumberOfUses(abilityChargeModifierDto.getModifier());
            charClassAbilityRepo.updateCharges(cca.getAbility().getName(), cca.getCharacter().getCharName(), cca.getCharClass().getName(), cca.getNumberOfUses());
            Gson gson = new Gson();
            String response = gson.toJson(cca);
            return response;
        } else {
            CharRaceAbility cra = charRaceAbilityRepo.findByCharacter_IdAndAbility_Name(abilityChargeModifierDto.getCharId(), abilityChargeModifierDto.getAbilName());
            cra.setNumberOfUses(abilityChargeModifierDto.getModifier());
            charRaceAbilityRepo.updateCharges(cra.getAbility().getName(), cra.getCharacter().getCharName(), cra.getRace(), cra.getNumberOfUses());
            Gson gson = new Gson();
            String response = gson.toJson(cra);
            return response;
        }
    }

    @Override
    public String updExp(NumberModifierDto numberModifierDto) {
        Character character = characterRepo.findByCharName(numberModifierDto.getCharName());
        Gson gson = new Gson();
        if (CharacterCalculator.getLevel(character.getExperience() + numberModifierDto.getModifier()) > CharacterCalculator.getLevel(character.getExperience())) {
            character.setIsLevelUpReady(true);
        }
        character.setExperience(character.getExperience() + numberModifierDto.getModifier());
        String response = gson.toJson(character);
        characterRepo.updateExp(character.getCharName(), character.getExperience());
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
