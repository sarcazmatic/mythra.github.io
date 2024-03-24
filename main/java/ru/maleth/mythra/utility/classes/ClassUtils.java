package ru.maleth.mythra.utility.classes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.utility.classes.artificer.ArtificerUtils;
import ru.maleth.mythra.utility.classes.barbarian.BarbarianUtils;
import ru.maleth.mythra.utility.classes.bard.BardUtils;
import ru.maleth.mythra.utility.classes.druid.DruidUtils;
import ru.maleth.mythra.utility.classes.monk.MonkUtils;
import ru.maleth.mythra.utility.classes.paladin.PaladinUtils;
import ru.maleth.mythra.utility.classes.ranger.RangerUtils;
import ru.maleth.mythra.utility.classes.rogue.RogueUtils;
import ru.maleth.mythra.utility.classes.warrior.WarriorUtils;
import ru.maleth.mythra.utility.classes.wizards.WizardUtils;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClassUtils {

    private final BardUtils bardUtils;
    private final BarbarianUtils barbarianUtils;
    private final WarriorUtils warriorUtils;
    private final DruidUtils druidUtils;
    private final MonkUtils monkUtils;
    private final PaladinUtils paladinUtils;
    private final RangerUtils rangerUtils;
    private final RogueUtils rogueUtils;
    private final WizardUtils wizardUtils;
    private final ArtificerUtils artificerUtils;



    public List<CharClassAbility> charClassAbilityFormer(Character character) {
        List<CharClass> characterClasses = character.getCharacterClasses().stream().toList();
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        for (CharClass charClass : characterClasses) {
            switch (charClass.getName()) {
                case "BARD" -> charClassAbilitiesList.addAll(bardUtils.formAbilities(character, charClass));
                case "BARBARIAN" -> charClassAbilitiesList.addAll(barbarianUtils.formAbilities(character, charClass));
                case "WARRIOR" -> charClassAbilitiesList.addAll(warriorUtils.formAbilities(character, charClass));
                case "DRUID" -> charClassAbilitiesList.addAll(druidUtils.formAbilities(character, charClass));
                case "MONK" -> charClassAbilitiesList.addAll(monkUtils.formAbilities(character, charClass));
                case "PALADIN" -> charClassAbilitiesList.addAll(paladinUtils.formAbilities(character, charClass));
                case "RANGER" -> charClassAbilitiesList.addAll(rangerUtils.formAbilities(character, charClass));
                case "ROGUE" -> charClassAbilitiesList.addAll(rogueUtils.formAbilities(character, charClass));
                case "WIZARD" -> charClassAbilitiesList.addAll(wizardUtils.formAbilities(character, charClass));
                case "ARTIFICER" -> charClassAbilitiesList.addAll(artificerUtils.formAbilities(character, charClass));
                default -> new ArrayList<>();
            }
        }
        log.info("Передаем собранные возможности персонажа " + character.getCharName() + " обратно в метод!");

        return charClassAbilitiesList;
    }

}
