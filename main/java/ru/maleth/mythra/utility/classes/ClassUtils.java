package ru.maleth.mythra.utility.classes;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.model.CharClassAbility;
import ru.maleth.mythra.model.CharClassLevel;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.repo.CharClassLevelRepo;
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

    private final CharClassLevelRepo charClassLevelRepo;
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
        log.info("Собираем абилки персонажа '{}' для вывода на чаршит", character.getCharName());
        //в дальнейшем добавить сет классов
        List<CharClassLevel> cclList = charClassLevelRepo.findAllByCharacter_IdOrderByCharClass(character.getId());
        log.info("Собираем список классов персонажа '{}'. Кол-во классов = {}", character.getCharName(), cclList.size());
        List<CharClassAbility> charClassAbilitiesList = new ArrayList<>();
        for (CharClassLevel ccl : cclList) {
            switch (ccl.getCharClass().getName()) {
                case "BARD" -> charClassAbilitiesList.addAll(bardUtils.formAbilities(ccl));
                case "BARBARIAN" -> charClassAbilitiesList.addAll(barbarianUtils.formAbilities(ccl));
                case "WARRIOR" -> charClassAbilitiesList.addAll(warriorUtils.formAbilities(ccl));
                case "DRUID" -> charClassAbilitiesList.addAll(druidUtils.formAbilities(ccl));
                case "MONK" -> charClassAbilitiesList.addAll(monkUtils.formAbilities(ccl));
                case "PALADIN" -> charClassAbilitiesList.addAll(paladinUtils.formAbilities(ccl));
                case "RANGER" -> charClassAbilitiesList.addAll(rangerUtils.formAbilities(character, character.getMainClass()));
                case "ROGUE" -> charClassAbilitiesList.addAll(rogueUtils.formAbilities(character, character.getMainClass()));
                case "WIZARD" -> charClassAbilitiesList.addAll(wizardUtils.formAbilities(character, character.getMainClass()));
                case "ARTIFICER" -> charClassAbilitiesList.addAll(artificerUtils.formAbilities(character, character.getMainClass()));
                default -> new ArrayList<>();
            }
        }
        log.info("Передаем собранные абилки персонажа '{}' обратно в чаршит", character.getCharName());
        return charClassAbilitiesList;
    }

}
