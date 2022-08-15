package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bashirov.studenttesting.models.Question;

@Repository
public interface QuestionsRepository extends JpaRepository<Question, Integer> {

}
