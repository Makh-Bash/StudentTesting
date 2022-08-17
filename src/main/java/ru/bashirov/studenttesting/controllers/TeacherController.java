package ru.bashirov.studenttesting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.bashirov.studenttesting.models.Test;
import ru.bashirov.studenttesting.models.User;
import ru.bashirov.studenttesting.sequrity.UserDetails;
import ru.bashirov.studenttesting.services.QuestionService;
import ru.bashirov.studenttesting.services.TestService;

import java.util.List;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TestService testService;

    private final QuestionService questionService;

    @Autowired
    public TeacherController(TestService testService, QuestionService questionService) {
        this.testService = testService;
        this.questionService = questionService;
    }

//    TODO поменять логику
    @GetMapping()
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userDetails.getUser();

        System.out.println(user.getRole() + " : " + user.getFirstName() + " : " + user.getId());

        List<Test> tests = testService.findAllByOwnerId(user.getId());

        model.addAttribute("tests", tests);

        return "teacher/tests";
    }

    @GetMapping("/new")
    public String create() {
        return "";
    }
}
