package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.maleth.mythra.model.CharClassAbility;

import java.util.List;


public interface CharClassAbilityRepo extends JpaRepository<CharClassAbility, Long> {

    List<CharClassAbility> findAllByCharacter_Id(Long charId);

    CharClassAbility findByCharacter_CharNameAndAbility_NameAndCharClass_Name(String name, String ability, String charClass);


    @Transactional
    @Modifying
    @Query("UPDATE CharClassAbility cca " +
            "SET cca.numberOfUses = :charges " +
            "WHERE cca.ability.name = :ability " +
            "AND cca.character.charName = :name " +
            "AND cca.charClass.name = :charClass")
    void updateCharges(String ability, String name, String charClass, Integer charges);

}
