package ru.bashirov.studenttesting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ru.bashirov.studenttesting.models.Question;
import ru.bashirov.studenttesting.models.Test;
import ru.bashirov.studenttesting.models.TestCategory;
import ru.bashirov.studenttesting.repositories.QuestionsRepository;
import ru.bashirov.studenttesting.repositories.TestCategoryRepository;
import ru.bashirov.studenttesting.repositories.TestsRepository;

import java.util.ArrayList;
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
        return testsRepository.findAll(Sort.by("title"));
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
        enrichTest(test);

        questionsRepository.saveAll(test.getQuestions());
        testsRepository.save(test);
    }

    private static void enrichTest(Test test) {
        test.setCountOfDecisions(0);
        test.setDateOfCreation(new Date());

        AtomicInteger counter = new AtomicInteger(1);
        test.getQuestions().forEach(question -> {
            question.setTest(test);
            question.setNumberOfQuestion(counter.getAndIncrement());
        });
    }

    @Transactional
    public void deleteById(Integer id) {
        questionsRepository.deleteAllByTestId(id);
        testsRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Test test) {
        test.setId(id);
        testsRepository.save(test);

        test.getQuestions().forEach(question -> questionsRepository
                .findById(question.getId())
                .ifPresent(value -> {
                    value.setTitle(question.getTitle());
                    value.setFirstAnswer(question.getFirstAnswer());
                    value.setSecondAnswer(question.getSecondAnswer());
                    value.setThirdAnswer(question.getThirdAnswer());
                    value.setFourthAnswer(question.getFourthAnswer());
                    value.setCorrectAnswerId(question.getCorrectAnswerId());

                    questionsRepository.save(value);
                }));
    }

    public List<Test> getTestsByTitleStartingWith(String query) {
        return testsRepository.findAllByTitleStartingWith(query);
    }

    public void setNewQuestions(Test test) {
        ArrayList<Question> questions = new ArrayList<>();
        for (int i = 0; i < test.getCountOfQuestions(); i++)
            questions.add(new Question());
        test.setQuestions(questions);
    }
}
