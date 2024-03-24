package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.maleth.mythra.enums.RaceEnum;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Race;

import java.util.List;


public interface CharRaceAbilityRepo extends JpaRepository<CharRaceAbility, Long> {

    List<CharRaceAbility> findByCharacter_IdOrderByAbility(Long id);

    CharRaceAbility findByCharacter_IdAndAbility_Name(Long id, String name);

    @Transactional
    @Modifying
    @Query("UPDATE CharRaceAbility cra " +
            "SET cra.numberOfUses = :charges " +
            "WHERE cra.ability.name = :ability " +
            "AND cra.character.charName = :name " +
            "AND cra.race.name = :raceName")
    void updateCharges(String ability, String name, RaceEnum raceName, Integer charges);

}
