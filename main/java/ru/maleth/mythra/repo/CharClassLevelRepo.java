package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.maleth.mythra.model.CharClass;
import ru.maleth.mythra.model.CharClassLevel;

import java.util.List;

public interface CharClassLevelRepo extends JpaRepository<CharClassLevel, Long> {

    List<CharClassLevel> findAllByCharacter_IdOrderByCharClass(long charId);

}
