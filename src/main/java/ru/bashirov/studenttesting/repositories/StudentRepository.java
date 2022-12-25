package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bashirov.studenttesting.models.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
}
