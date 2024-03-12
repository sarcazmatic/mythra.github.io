package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maleth.mythra.model.Ability;

import java.util.List;

public interface AbilityRepo extends JpaRepository<Ability, Long> {

    Ability findByName(String name);

    @Query("SELECT a FROM Ability a " +
            "LEFT JOIN CharClass AS c ON c.id = a.charClass.id " +
            "WHERE (:charClass IS NOT NULL AND c.name = :charClass) " +
            "AND (:level IS NOT NULL AND a.classLevel = :level)")
    List<Ability> findAllByCharClassAndClassLevel(String charClass, Integer level);


}
