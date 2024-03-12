package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.maleth.mythra.model.Ability;

import java.util.List;

public interface AbilityRepo extends JpaRepository<Ability, Long> {

    Ability findByName(String name);

}
