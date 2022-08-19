package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bashirov.studenttesting.models.Test;

import java.util.List;

@Repository
public interface TestsRepository extends JpaRepository<Test, Integer> {
    List<Test> findAllByOwnerId(int id);

    List<Test> findAllByTitleStartingWith(String startingWith);
}
