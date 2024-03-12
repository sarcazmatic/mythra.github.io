package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.model.Race;

public interface RaceRepo extends JpaRepository<Race, Long> {

    Race findByName(String name);

}
