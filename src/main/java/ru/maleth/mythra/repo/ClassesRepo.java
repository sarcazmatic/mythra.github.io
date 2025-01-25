package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maleth.mythra.model.CharClass;

@Repository
public interface ClassesRepo extends JpaRepository<CharClass, Long> {

    CharClass findByName(String name);

}
