package ru.maleth.mythra.utility.classes.rogue;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.enums.AbilityEnum;
import ru.maleth.mythra.enums.ActionCostEnum;
import ru.maleth.mythra.enums.RestEnum;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.AbilityRepo;
import ru.maleth.mythra.repo.CharClassAbilityRepo;
import ru.maleth.mythra.utility.CharacterCalculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class RogueUtils {

    private final AbilityRepo abilityRepo;
    private final CharClassAbilityRepo charClassAbilityRepo;

    public List<CharClassAbility> formAbilities(Character character, CharClass charClass) {
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        String name;
        int characterLevel = CharacterCalculator.getLevel(character.getExperience());
        if (characterLevel >= 1 && characterLevel <= 2) {
            name = "СКРЫТАЯ АТАКА (1к6)";
        } else if (characterLevel >= 3 && characterLevel <= 4) {
            name = "СКРЫТАЯ АТАКА (2к6)";
        } else if (characterLevel >= 5 && characterLevel <= 6) {
            name = "СКРЫТАЯ АТАКА (3к6)";
        } else if (characterLevel >= 7 && characterLevel <= 8) {
            name = "СКРЫТАЯ АТАКА (4к6)";
        } else if (characterLevel >= 9 && characterLevel <= 10) {
            name = "СКРЫТАЯ АТАКА (5к6)";
        } else if (characterLevel >= 11 && characterLevel <= 12) {
            name = "СКРЫТАЯ АТАКА (6к6)";
        } else if (characterLevel >= 13 && characterLevel <= 14) {
            name = "СКРЫТАЯ АТАКА (7к6)";
        } else if (characterLevel >= 15 && characterLevel <= 16) {
            name = "СКРЫТАЯ АТАКА (8к6)";
        } else if (characterLevel >= 17 && characterLevel <= 18) {
            name = "СКРЫТАЯ АТАКА (9к6)";
        } else {
            name = "СКРЫТАЯ АТАКА (10к6)";
        }
        int numberOfUses = 0;
        List<Optional<Ability>> optionalAbilities = new ArrayList<>();
        Optional<Ability> abilityNoArmorOpt = Optional.ofNullable(abilityRepo.findByName(name));
        Optional<Ability> abilityArtOfFight = Optional.ofNullable(abilityRepo.findByName("ВОРОВСКОЙ ЖАРГОН"));
        optionalAbilities.add(abilityNoArmorOpt);
        optionalAbilities.add(abilityArtOfFight);
        for (int i = 0; i < optionalAbilities.size(); i++) {
            Ability ability = new Ability();
            if (optionalAbilities.get(i).isEmpty()) {
                switch (i) {
                    case 0 -> {
                        ability = Ability.builder()
                                .name(name)
                                .description("""
                                        Начиная с 1 уровня вы знаете, как точно наносить удар и использовать отвлечение \
                                        врага. Один раз в ход вы можете причинить дополнительный урон 1к6 одному из \
                                        существ, по которому вы попали атакой, совершённой с преимуществом к броску \
                                        атаки. Атака должна использовать дальнобойное оружие или оружие со свойством \
                                        «фехтовальное».
                                        Вам не нужно иметь преимущество при броске атаки, если другой враг цели находится \
                                        в пределах 5 футов от неё. Этот враг не должен быть недееспособным, и у вас не \
                                        должно быть помехи для броска атаки.
                                        Дополнительный урон увеличивается, когда вы получаете уровни в этом классе, как \
                                        показано в колонке «скрытая атака».""")
                                .isActive(false)
                                .requiresRest(false)
                                .typeOfRest(RestEnum.NONE)
                                .abilitySource(AbilityEnum.CLASS)
                                .cost(ActionCostEnum.BLANK)
                                .build();
                        abilityRepo.save(ability);
                    }
                    case 1 -> {
                        ability = Ability.builder()
                                .name("ВОРОВСКОЙ ЖАРГОН")
                                .description("""
                                        Во время плутовского обучения вы выучили воровской жаргон, тайную смесь диалекта, \
                                        жаргона и шифра, который позволяет скрывать сообщения в, казалось бы, обычном \
                                        разговоре. Только другое существо, знающее воровской жаргон, понимает такие сообщения. \
                                        Это занимает в четыре раза больше времени, нежели передача тех же слов прямым текстом.
                                        Кроме того, вы понимаете набор секретных знаков и символов, используемый для передачи \
                                        коротких и простых сообщений. Например, является ли область опасной или территорией \
                                        гильдии воров, находится ли поблизости добыча, простодушны ли люди в округе, и \
                                        предоставляют ли здесь безопасное убежище для воров в бегах.""")
                                .isActive(false)
                                .requiresRest(false)
                                .typeOfRest(RestEnum.NONE)
                                .abilitySource(AbilityEnum.CLASS)
                                .cost(ActionCostEnum.BLANK)
                                .build();
                        abilityRepo.save(ability);
                    }
                }
            } else {
                ability = optionalAbilities.get(i).get();
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
        }
        return charClassAbilitiesList;
    }
}