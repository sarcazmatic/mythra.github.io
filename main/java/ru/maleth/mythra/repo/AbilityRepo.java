package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.model.Ability;

public interface AbilityRepo extends JpaRepository<Ability, Long> {

    Ability findByName(String name);

}
