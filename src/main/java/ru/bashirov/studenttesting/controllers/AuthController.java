package ru.bashirov.studenttesting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.bashirov.studenttesting.models.User;
import ru.bashirov.studenttesting.services.RegistrationService;
import ru.bashirov.studenttesting.util.UserValidator;

import javax.validation.Valid;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final RegistrationService registrationService;

    private final UserValidator userValidator;

    @Autowired
    public AuthController(RegistrationService registrationService, UserValidator userValidator) {
        this.registrationService = registrationService;
        this.userValidator = userValidator;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "auth/registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("user") @Valid User user,
                                      BindingResult bindingResult,
                                      @RequestParam("role") String role) {

        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors())
            return "auth/registration";

        user.setRole(role.equals("teacher") ? "ROLE_TEACHER" : "ROLE_USER");
        registrationService.register(user);

        return "redirect:/auth/login";
    }


}
