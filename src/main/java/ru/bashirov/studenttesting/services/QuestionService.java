package ru.bashirov.studenttesting.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bashirov.studenttesting.models.Question;
import ru.bashirov.studenttesting.repositories.QuestionsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionsRepository questionsRepository;

    @Autowired
    public QuestionService(QuestionsRepository questionsRepository) {
        this.questionsRepository = questionsRepository;
    }

    public List<Question> findAllByTestId(int id) {
        return questionsRepository.findByTestId(id, Sort.by("numberOfQuestion"));
    }
}
