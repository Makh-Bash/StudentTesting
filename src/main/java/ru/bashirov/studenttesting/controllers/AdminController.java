package ru.bashirov.studenttesting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.bashirov.studenttesting.models.Test;
import ru.bashirov.studenttesting.models.User;
import ru.bashirov.studenttesting.services.TestService;
import ru.bashirov.studenttesting.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final TestService testService;

    @Autowired
    public AdminController(UserService userService, TestService testService) {
        this.userService = userService;
        this.testService = testService;
    }

// TODO 1 объект вместо полей
    @GetMapping()
    public String home(Model model) {
        model.addAttribute("usersCount", userService.getUsersCount());
        model.addAttribute("studentsCount", userService.getStudentsCount());
        model.addAttribute("teachersCount", userService.getTeachersCount());

        return "admin/home";
    }

    @GetMapping("/tests")
    public String checkTests() {
        return "admin/tests";
    }

    @PostMapping("/tests")
    public String findTests(@RequestParam("query") String query, Model model) {
        List<Test> tests = testService.getTestsByTitleStartingWith(query);
        model.addAttribute("tests", tests);
        return "admin/tests";
    }

    @DeleteMapping("tests/{id}")
    public String deleteTest(@PathVariable("id") int id) {
        testService.deleteById(id);
        return "redirect:/admin/tests";
    }

    @GetMapping("/users")
    public String checkUsers() {
        return "admin/users";
    }

    @PostMapping("/users")
    public String findUsers(@RequestParam("query") String query, Model model) {
        List<User> users = userService.getUsersByLoginStartingWith(query);
        model.addAttribute("users", users);
        return "admin/users";
    }

    @PatchMapping("users/{id}")
    public String baneUser(@PathVariable("id") int id) {
        userService.baneUserById(id);
        return "redirect:/admin/users";
    }

    @DeleteMapping("users/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        userService.deleteById(id);
        return "redirect:/admin/users";
    }

}
