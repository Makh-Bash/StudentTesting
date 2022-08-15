package ru.bashirov.studenttesting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bashirov.studenttesting.repositories.DecisionsRepository;

@Service
public class DecisionService {

    private final DecisionsRepository decisionsRepository;

    @Autowired
    public DecisionService(DecisionsRepository decisionsRepository) {
        this.decisionsRepository = decisionsRepository;
    }
}
