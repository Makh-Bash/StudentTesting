package ru.bashirov.studenttesting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bashirov.studenttesting.models.Test;
import ru.bashirov.studenttesting.repositories.TestsRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TestService {

    private final TestsRepository testsRepository;

    @Autowired
    public TestService(TestsRepository testsRepository) {
        this.testsRepository = testsRepository;
    }

    public List<Test> findAll() {
        return testsRepository.findAll();
    }

    public Optional<Test> findById(int id) {
        return testsRepository.findById(id);
    }

    public List<Test> findAllByOwnerId(int id) {
        return testsRepository.findAllByOwnerId(id);
    }


    @Transactional
    public void countUp(int id) {
        Optional<Test> findTest = testsRepository.findById(id);
        findTest.ifPresent(test -> test.setCountOfDecisions(test.getCountOfDecisions() + 1));
    }
}
