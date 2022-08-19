package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bashirov.studenttesting.models.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);

    long count();

    long countByRole(String role);

    List<User> findByLoginStartingWith(String startingWith);
}
