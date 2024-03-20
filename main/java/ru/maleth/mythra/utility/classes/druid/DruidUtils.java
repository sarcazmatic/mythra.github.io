package ru.maleth.mythra.utility.classes.druid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DruidUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(List<CharClassAbility> charClassAbilitiesList, Ability ability,
                                                Character character, CharClass charClass) {
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        int numberOfUses = 1000;
        if (characterLevel >= 3 && characterLevel <= 5) {
            numberOfUses = 3;
        } else if (characterLevel >= 6 && characterLevel <= 11) {
            numberOfUses = 4;
        } else if (characterLevel >= 12 && characterLevel <= 16) {
            numberOfUses = 5;
        } else if (characterLevel >= 17 && characterLevel <= 19) {
            numberOfUses = 6;
        } else if (characterLevel >= 20) {
            numberOfUses = 1000;
        }
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName("ЯРОСТЬ"));
        if (abilityPresent.isEmpty()) {
            ability = Ability.builder()
                    .name("ДРУИДИЧЕСКИЙ ЯЗЫК")
                    .classLevel(characterLevel)
                    .description("""
                            Вы знаете Друидический язык — тайный язык друидов. Вы можете на нём говорить и оставлять \
                            тайные послания. Вы и все, кто знают этот язык, автоматически замечаете эти послания. \
                            Другие замечают присутствие послания при успешной проверке Мудрости (Внимательность) со Сл 15, \
                            но без помощи магии не могут расшифровать его.""")
                    .isActive(true)
                    .requiresRest(true)
                    .typeOfRest(RestEnum.NONE)
                    .charClass(charClass)
                    .cost(ActionCostEnum.FREE_ACTION)
                    .build();
            abilityRepo.save(ability);
        } else {
            ability = abilityPresent.get();
        }
        Optional<CharClassAbility> ccaOptional = Optional.ofNullable(charClassAbilityRepo.findByCharacter_CharNameAndAbility_NameAndCharClass_Name(character.getCharName(), ability.getName(), charClass.getName()));
        CharClassAbility charClassAbility;
        if (ccaOptional.isEmpty()) {
            charClassAbility = CharClassAbility.builder()
                    .character(character)
                    .ability(ability)
                    .charClass(charClass)
                    .numberOfUses(numberOfUses)
                    .build();
            charClassAbilityRepo.save(charClassAbility);
        } else {
            charClassAbility = ccaOptional.get();
        }
        charClassAbilitiesList.add(charClassAbility);
        return charClassAbilitiesList;
    }
}