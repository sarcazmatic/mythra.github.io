package ru.maleth.mythra.utility.races;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.Race;
import ru.maleth.mythra.utility.races.aarakocra.AarakocraUtils;
import ru.maleth.mythra.utility.races.aasimar.FallenAasimarUtils;
import ru.maleth.mythra.utility.races.aasimar.ProtectorAasimarUtils;
import ru.maleth.mythra.utility.races.aasimar.ScourgeAasimarUtils;
import ru.maleth.mythra.utility.races.dragonborn.DragonbornUtils;
import ru.maleth.mythra.utility.races.dwarves.HillDwarfUtils;
import ru.maleth.mythra.utility.races.dwarves.MountainDwarfUtils;
import ru.maleth.mythra.utility.races.elves.DrowUtils;
import ru.maleth.mythra.utility.races.elves.HighElfUtils;
import ru.maleth.mythra.utility.races.elves.WoodElfUtils;
import ru.maleth.mythra.utility.races.gith.GithyankiUtils;
import ru.maleth.mythra.utility.races.gith.GithzeraiUtils;
import ru.maleth.mythra.utility.races.gnomes.ForestGnomeUtils;
import ru.maleth.mythra.utility.races.gnomes.RockGnomesUtils;
import ru.maleth.mythra.utility.races.goliath.GoliathUtils;
import ru.maleth.mythra.utility.races.halfelves.HalfelfUtils;
import ru.maleth.mythra.utility.races.halflings.LightfootHalflingUtils;
import ru.maleth.mythra.utility.races.halflings.StoutHalflingUtils;
import ru.maleth.mythra.utility.races.halforcs.HalforcUtils;
import ru.maleth.mythra.utility.races.humans.HumanUtils;
import ru.maleth.mythra.utility.races.tieflings.TieflingUtils;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RaceUtils {

    private final AarakocraUtils aarakocraUtils;
    private final FallenAasimarUtils fallenAasimarUtils;
    private final ProtectorAasimarUtils protectorAasimarUtils;
    private final ScourgeAasimarUtils scourgeAasimarUtils;
    private final DragonbornUtils dragonbornUtils;
    private final HillDwarfUtils hillDwarfUtils;
    private final MountainDwarfUtils mountainDwarfUtils;
    private final WoodElfUtils woodElfUtils;
    private final HighElfUtils highElfUtils;
    private final DrowUtils drowUtils;
    private final GithyankiUtils githyankiUtils;
    private final GithzeraiUtils githzeraiUtils;
    private final ForestGnomeUtils forestGnomeUtils;
    private final RockGnomesUtils rockGnomesUtils;
    private final GoliathUtils goliathUtils;
    private final HalfelfUtils halfelfUtils;
    private final StoutHalflingUtils stoutHalflingUtils;
    private final LightfootHalflingUtils lightfootHalflingUtils;
    private final HalforcUtils halforcUtils;
    private final HumanUtils humanUtils;
    private final TieflingUtils tieflingUtils;


    public List<CharRaceAbility> charRaceAbilityFormer(Character character) {
        Race race = character.getCharRace();
        return switch (race.getRaceEnum()) {
            case AARAKOCRA -> aarakocraUtils.formAbilities(character);
            case FALLEN_AASIMAR -> fallenAasimarUtils.formAbilities(character);
            case PROTECTOR_AASIMAR -> protectorAasimarUtils.formAbilities(character);
            case SCOURGE_AASIMAR -> scourgeAasimarUtils.formAbilities(character);
            case BLACK_DRAGONBORN, BLUE_DRAGONBORN, BRASS_DRAGONBORN, BRONZE_DRAGONBORN, COPPER_DRAGONBORN, GOLD_DRAGONBORN, GREEN_DRAGONBORN, RED_DRAGONBORN, SILVER_DRAGONBORN, WHITE_DRAGONBORN ->
                    dragonbornUtils.formAbilities(character);
            case HILL_DWARF -> hillDwarfUtils.formAbilities(character);
            case MOUNTAIN_DWARF -> mountainDwarfUtils.formAbilities(character);
            case WOOD_ELF -> woodElfUtils.formAbilities(character);
            case HIGH_ELF -> highElfUtils.formAbilities(character);
            case DROW -> drowUtils.formAbilities(character);
            case GITHYANKI -> githyankiUtils.formAbilities(character);
            case GITHZERAI -> githzeraiUtils.formAbilities(character);
            case FOREST_GNOME -> forestGnomeUtils.formAbilities(character);
            case ROCK_GNOME -> rockGnomesUtils.formAbilities(character);
            case GOLIATH -> goliathUtils.formAbilities(character);
            case HALFELF -> halfelfUtils.formAbilities(character);
            case STOUT_HALFLING -> stoutHalflingUtils.formAbilities(character);
            case LIGHTFOOT_HALFLING -> lightfootHalflingUtils.formAbilities(character);
            case HALFORC -> halforcUtils.formAbilities(character);
            case HUMAN -> humanUtils.formAbilities(character);
            case TIEFLING -> tieflingUtils.formAbilities(character);
            default -> throw new RuntimeException("Не хочу покамест с расами прописывать все");
        };
    }

}
