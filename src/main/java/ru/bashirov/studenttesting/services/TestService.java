package ru.bashirov.studenttesting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bashirov.studenttesting.models.Question;
import ru.bashirov.studenttesting.models.Test;
import ru.bashirov.studenttesting.models.TestCategory;
import ru.bashirov.studenttesting.models.User;
import ru.bashirov.studenttesting.repositories.QuestionsRepository;
import ru.bashirov.studenttesting.repositories.TestCategoryRepository;
import ru.bashirov.studenttesting.repositories.TestsRepository;
import ru.bashirov.studenttesting.sequrity.UserDetails;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@Transactional(readOnly = true)
public class TestService {

    private final TestsRepository testsRepository;

    private final QuestionsRepository questionsRepository;

    private final TestCategoryRepository testCategoryRepository;

    @Autowired
    public TestService(TestsRepository testsRepository, QuestionsRepository questionsRepository, TestCategoryRepository testCategoryRepository) {
        this.testsRepository = testsRepository;
        this.questionsRepository = questionsRepository;
        this.testCategoryRepository = testCategoryRepository;
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

    public List<TestCategory> getAllCategories() {
        return testCategoryRepository.findAll();
    }



    @Transactional
    public void save(Test test) {
        test.setCountOfDecisions(0);
        test.setDateOfCreation(new Date());

        AtomicInteger counter = new AtomicInteger(1);
        test.getQuestions().forEach(question -> {
            question.setTest(test);
            question.setNumber_of_question(counter.getAndIncrement());
        });

        questionsRepository.saveAll(test.getQuestions());

        testsRepository.save(test);
    }

    @Transactional
    public void deleteById(Integer id) {
        System.out.println("Удаление элементов");
        questionsRepository.deleteAllByTestId(id);
        testsRepository.deleteById(id);
    }

    //   TODO рефактор
    @Transactional
    public void update(int id, Test test) {
        test.setId(id);
        testsRepository.save(test);
        test.getQuestions().forEach(question -> {
            questionsRepository
                    .findById(question.getId())
                    .ifPresent(value -> {
                        value.setTitle(question.getTitle());
                        value.setFirstAnswer(question.getFirstAnswer());
                        value.setSecondAnswer(question.getSecondAnswer());
                        value.setThirdAnswer(question.getThirdAnswer());
                        value.setFourthAnswer(question.getFourthAnswer());
                        value.setCorrectAnswerId(question.getCorrectAnswerId());

                        questionsRepository.save(value);
                    });
        });
    }
}
