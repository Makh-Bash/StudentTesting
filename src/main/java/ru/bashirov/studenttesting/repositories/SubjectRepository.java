package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bashirov.studenttesting.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
