package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bashirov.studenttesting.models.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {
}
