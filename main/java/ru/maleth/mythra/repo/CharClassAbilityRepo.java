package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.maleth.mythra.model.Ability;
import ru.maleth.mythra.model.CharClassAbility;

import java.util.List;


public interface CharClassAbilityRepo extends JpaRepository<CharClassAbility, Long> {

    List<CharClassAbility> findAllByCharacter_IdOrderByAbilityAsc(Long charId);

    CharClassAbility findByCharacter_IdAndAbility_Name(Long charId, String name);

    @Transactional
    @Modifying
    @Query("UPDATE CharClassAbility cca " +
            "SET cca.numberOfUses = :charges " +
            "WHERE cca.ability.name = :ability " +
            "AND cca.character.charName = :name " +
            "AND cca.charClass.name = :charClass")
    void updateCharges(String ability, String name, String charClass, Integer charges);

}
