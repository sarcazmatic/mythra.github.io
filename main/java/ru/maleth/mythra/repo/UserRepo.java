package ru.maleth.mythra.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.maleth.mythra.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u " +
            "WHERE ((:name IS NOT NULL AND u.name = :name) OR (:name IS NULL)) " +
            "OR ((:email IS NOT NULL AND u.email = :email) OR (:email IS NULL))")
    Optional<User> findByNameOrEmail(@Param("name") String name, @Param("email") String email);

    Optional<User> findByName(String name);

}
