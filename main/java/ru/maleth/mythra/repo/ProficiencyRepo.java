package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maleth.mythra.model.Proficiency;

@Repository
public interface ProficiencyRepo extends JpaRepository<Proficiency, Long> {

    Proficiency findByName(String name);

}
