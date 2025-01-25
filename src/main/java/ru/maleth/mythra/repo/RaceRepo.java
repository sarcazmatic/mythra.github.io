package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.enums.RaceEnum;
import ru.maleth.mythra.model.Race;

public interface RaceRepo extends JpaRepository<Race, Long> {

    Race findByRaceEnum(RaceEnum name);

}
