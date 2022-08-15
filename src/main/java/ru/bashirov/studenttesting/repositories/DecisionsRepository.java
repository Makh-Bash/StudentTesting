package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bashirov.studenttesting.models.Decision;

@Repository
public interface DecisionsRepository extends JpaRepository<Decision, Integer> {

}
