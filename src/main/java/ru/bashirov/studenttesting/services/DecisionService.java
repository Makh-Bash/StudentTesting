package ru.bashirov.studenttesting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bashirov.studenttesting.models.AnswersList;
import ru.bashirov.studenttesting.models.Decision;
import ru.bashirov.studenttesting.models.Test;
import ru.bashirov.studenttesting.repositories.DecisionsRepository;

import java.util.Date;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DecisionService {

    private final DecisionsRepository decisionsRepository;
    private final UserService userService;

    @Autowired
    public DecisionService(DecisionsRepository decisionsRepository, UserService userService) {
        this.decisionsRepository = decisionsRepository;
        this.userService = userService;
    }

    @Transactional
    public void save(AnswersList answersList, int rightAnswersCount) {
        Decision decision = createDecision(answersList, rightAnswersCount);
        decisionsRepository.save(decision);
    }

    private Decision createDecision(AnswersList answersList, int rightAnswersCount) {
        Decision decision = new Decision();
        decision.setPerson(userService.getCurrentUser());
        decision.setTest(answersList.getAnswers().get(0).getTest());
        decision.setCountOfRightAnswers(rightAnswersCount);
        decision.setCountOfAllAnswers(answersList.getAllQuestionsCount());
        decision.setTimeOfDecision(new Date());

        return decision;
    }

    public List<Decision> findAllByUserId() {
        return decisionsRepository.findAllByPerson(userService.getCurrentUser());
    }

    public int findCountOfDecisionsByUser(Test test) {
        return decisionsRepository.countByPersonAndTest(userService.getCurrentUser(), test);
    }

    public int findBestDecisionByUser(Test test) {

        Integer result = decisionsRepository.findMaxCountOfRightAnswersByPersonAndTest(
                test.getId(),
                userService.getCurrentUser().getId());

        return result == null ? 0 : result;

    }
}
