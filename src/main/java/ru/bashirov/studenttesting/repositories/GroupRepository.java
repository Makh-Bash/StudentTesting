package ru.bashirov.studenttesting.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.bashirov.studenttesting.models.Group;

public interface GroupRepository extends JpaRepository<Group, Integer> {
}
