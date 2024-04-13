package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.maleth.mythra.model.CharRaceAbility;
import ru.maleth.mythra.model.Character;
import ru.maleth.mythra.model.Race;

import java.util.List;


public interface CharRaceAbilityRepo extends JpaRepository<CharRaceAbility, Long> {

    @Query("SELECT cra FROM CharRaceAbility cra " +
            "JOIN Race r on cra.race.id=r.id " +
            "JOIN Ability a on cra.ability.id=a.id " +
            "AND cra.character = :character " +
            "WHERE cra.race = :race " +
            "AND cra.ability.levelAvailable <= :level " +
            "ORDER BY cra.ability.name asc")
    List<CharRaceAbility> findAllByCharacter_IdAndRaceLimitByLevelOrderByAbility_Name(Character character, Race race, Integer level);

    CharRaceAbility findByCharacter_IdAndAbility_Name(Long id, String name);

    @Transactional
    @Modifying
    @Query("UPDATE CharRaceAbility cra " +
            "SET cra.numberOfUses = :charges " +
            "WHERE cra.ability.name = :ability " +
            "AND cra.character.charName = :name " +
            "AND cra.race = :race")
    void updateCharges(String ability, String name, Race race, Integer charges);

}
