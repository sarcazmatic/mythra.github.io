package ru.maleth.mythra.utility.classes.druid;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.*;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.repo.ClassesRepo;
import ru.maleth.mythra.service.character.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DruidUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;
    private final ClassesRepo classesRepo;

    public List<CharClassAbility> formAbilities(CharClassLevel ccl) {
        Character character = ccl.getCharacter();
        CharClass charClass = classesRepo.findByName("DRUID");
        Integer level = ccl.getClassLevel();
        List<CharClassAbility> ccaList = new ArrayList<>();
        List<Ability> abilities = abilityRepo.findAllByClassLimitByLevel("DRUID", level);
        for (Ability a : abilities) {
            Optional<CharClassAbility> ccaOptional = Optional.ofNullable(charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), a.getName()));
            CharClassAbility cca;
            if (ccaOptional.isEmpty()) {
                cca = CharClassAbility.builder()
                        .ability(a)
                        .charClass(charClass)
                        .character(character)
                        .build();
                switch (a.getName()) {
                    default -> cca.setNumberOfUses(0);
                }
                charClassAbilityRepo.save(cca);
            } else {
                cca = ccaOptional.get();
            }
            ccaList.add(cca);
        }
        return ccaList;
    }

}
    /*
    public List<CharClassAbility> formAbilities(Character character, CharClass charClass) {
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        Ability ability;
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        int numberOfUses = 0;
        Optional<Ability> abilityPresent = Optional.ofNullable(abilityRepo.findByName("ДРУИДИЧЕСКИЙ ЯЗЫК"));
        if (abilityPresent.isEmpty()) {
            ability = Ability.builder()
                    .name("ДРУИДИЧЕСКИЙ ЯЗЫК")
                    .description("""
                            Вы знаете Друидический язык — тайный язык друидов. Вы можете на нём говорить и оставлять \
                            тайные послания. Вы и все, кто знают этот язык, автоматически замечаете эти послания. \
                            Другие замечают присутствие послания при успешной проверке Мудрости (Внимательность) со Сл 15, \
                            но без помощи магии не могут расшифровать его.""")
                    .isActive(false)
                    .requiresRest(false)
                    .typeOfRest(RestEnum.NONE)
                    .abilitySource(AbilityEnum.CLASS)
                    .cost(ActionCostEnum.BLANK)
                    .build();
            abilityRepo.save(ability);
        } else {
            ability = abilityPresent.get();
        }
        Optional<CharClassAbility> ccaOptional = Optional.ofNullable(charClassAbilityRepo.findByCharacter_IdAndAbility_Name(character.getId(), ability.getName()));
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
*/