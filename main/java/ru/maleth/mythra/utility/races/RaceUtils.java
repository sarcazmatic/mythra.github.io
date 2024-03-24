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

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class RaceUtils {

    private final AarakocraUtils aarakocraUtils;
    private final FallenAasimarUtils fallenAasimarUtils;
    private final ProtectorAasimarUtils protectorAasimarUtils;
    private final ScourgeAasimarUtils scourgeAasimarUtils;

    public List<CharRaceAbility> charRaceAbilityFormer(Character character) {
        Race race = character.getCharRace();
        switch (race.getName()) {
            case AARAKOCRA: return aarakocraUtils.formAbilities(character);
            case FALLEN_AASIMAR: return fallenAasimarUtils.formAbilities(character);
            case PROTECTOR_AASIMAR: return protectorAasimarUtils.formAbilities(character);
            case SCOURGE_AASIMAR: return scourgeAasimarUtils.formAbilities(character);
            default: throw new RuntimeException("Не хочу покамест с расами прописывать все");
        }
    }

}
