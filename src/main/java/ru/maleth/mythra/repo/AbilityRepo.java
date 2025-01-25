package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maleth.mythra.enums.RaceEnum;
import ru.maleth.mythra.model.Ability;

import java.util.List;

public interface AbilityRepo extends JpaRepository<Ability, Long> {

    Ability findByName(String name);

    @Query("SELECT a FROM Ability a " +
            "WHERE a.charClass.name = :className " +
            "AND a.levelAvailable <= :level")
    List<Ability> findAllByClassLimitByLevel(String className, Integer level);

    @Query("SELECT a FROM Ability a " +
            "WHERE a.race.raceEnum = :race " +
            "AND a.levelAvailable <= :level")
    List<Ability> findAllByRaceLimitByLevel(RaceEnum race, Integer level);

}
