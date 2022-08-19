package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bashirov.studenttesting.models.TestCategory;

@Repository
public interface TestCategoryRepository extends JpaRepository<TestCategory, Integer> {

}
