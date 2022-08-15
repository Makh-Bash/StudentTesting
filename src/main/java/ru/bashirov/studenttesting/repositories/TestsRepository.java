package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bashirov.studenttesting.models.Test;

@Repository
public interface TestsRepository extends JpaRepository<Test, Integer> {

}
