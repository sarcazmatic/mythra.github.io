package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maleth.mythra.model.CharClassAbility;

import java.util.List;

public interface CharClassAbilityRepo extends JpaRepository<CharClassAbility, Long> {

    @Query("SELECT cca FROM CharClassAbility cca " +
            "LEFT JOIN Ability AS a ON a.id = cca.ability.id "+
            "LEFT JOIN Character AS c ON c.id = cca.character.id " +
            "LEFT JOIN CharClass AS cc ON c.id = cca.charClass.id " +
            "WHERE (:name IS NOT NULL AND c.charName = :name) " +
            "AND (:charClass IS NOT NULL AND cc.name = :charClass)")
    List<CharClassAbility> findAllByCharacterNameAndCharacterClass(String name, String charClass);

}
