package ru.bashirov.studenttesting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bashirov.studenttesting.models.AnswersList;
import ru.bashirov.studenttesting.models.Question;
import ru.bashirov.studenttesting.services.DecisionService;
import ru.bashirov.studenttesting.services.QuestionService;
import ru.bashirov.studenttesting.services.TestService;

import java.util.ArrayList;

@Controller
@RequestMapping("/test")
public class TestController {

    private final TestService testService;

    private final QuestionService questionService;

    private final DecisionService decisionService;

    @Autowired
    public TestController(TestService testService, QuestionService questionService, DecisionService decisionService) {
        this.testService = testService;
        this.questionService = questionService;
        this.decisionService = decisionService;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("tests", testService.findAll());
        return "tests/index";
    }

    @GetMapping("/{id}")
    public String info(@PathVariable("id") int id,
                       Model model) {
        model.addAttribute("test", testService.findById(id));
        return "tests/info";
    }

    @GetMapping("/{id}/show")
    public String show(@PathVariable("id") int id,
                       Model model) {

        AnswersList answersList = new AnswersList((ArrayList<Question>) questionService.findAllByTestId(id));
        answersList.setAllQuestionsCount(answersList.getAnswers().size());

        model.addAttribute("answersList", answersList);
        return "tests/show";
    }

    @PostMapping("/{id}/show")
    public String check(@ModelAttribute AnswersList answersList ,
                        Model model, @PathVariable int id) {

        int rightAnswersCount = (int) answersList.getAnswers().stream().filter(Question::isRight).count();
        String resultInfo = rightAnswersCount + "/" + answersList.getAllQuestionsCount();


        decisionService.save(answersList);
        testService.countUp(id);
        model.addAttribute("resultInfo", resultInfo);
        return "/tests/result";
    }



}
