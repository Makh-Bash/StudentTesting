package ru.bashirov.studenttesting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bashirov.studenttesting.models.Question;
import ru.bashirov.studenttesting.models.Test;
import ru.bashirov.studenttesting.services.TestService;
import ru.bashirov.studenttesting.services.UserService;

import javax.validation.Valid;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/teacher")
public class TeacherController {

    private final TestService testService;

    private final UserService userService;


    @Autowired
    public TeacherController(TestService testService, UserService userService) {
        this.testService = testService;
        this.userService = userService;
    }

    @GetMapping()
    public String index(Model model) {
        List<Test> tests = testService.findAllByOwnerId(userService.getCurrentUser().getId());
        model.addAttribute("tests", tests);
        return "teacher/tests";
    }


    @PatchMapping("/{id}")
    public String update(@PathVariable("id") int id,
                         @ModelAttribute("test") @Valid Test test,
                         BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", testService.getAllCategories());
            return "teacher/questions";
        } else {
            testService.update(id, test);
            return "redirect:/teacher";
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        testService.deleteById(id);
        return "redirect:/teacher";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Test> test = testService.findById(id);

        if (test.isPresent()) {
            test.get().getQuestions().sort(Comparator.comparingInt(Question::getNumberOfQuestion));

            model.addAttribute("test", test.get());
            return "tests/edit";
        }

        return "redirect:/teacher";
    }

    @GetMapping("/new")
    public String create(Model model) {
        Test test = new Test();
        test.setOwner(userService.getCurrentUser());

        model.addAttribute("test", test);
        model.addAttribute("categories", testService.getAllCategories());
        return "teacher/new";
    }

    @PostMapping("/new")
    public String check(@ModelAttribute @Valid Test test,
                        BindingResult bindingResult,
                        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", testService.getAllCategories());
            return "teacher/new";
        } else {
            testService.setNewQuestions(test);

            model.addAttribute("test", test);
            return "teacher/questions";
        }
    }

    @PostMapping("/add")
    public String add(@ModelAttribute("test") @Valid Test test,
                      BindingResult bindingResult,
                      Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", testService.getAllCategories());
            return "teacher/questions";

        } else {
            testService.save(test);
            return "main/index";
        }
    }
}
