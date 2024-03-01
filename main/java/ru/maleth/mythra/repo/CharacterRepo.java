package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maleth.mythra.model.Character;

@Repository
public interface CharacterRepo extends JpaRepository<Character, Long> {
}
