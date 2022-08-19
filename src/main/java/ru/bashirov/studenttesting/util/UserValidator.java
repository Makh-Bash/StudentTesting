package ru.bashirov.studenttesting.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNullApi;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.bashirov.studenttesting.models.User;
import ru.bashirov.studenttesting.services.UserDetailService;

import javax.validation.constraints.NotNull;

@Component
public class UserValidator implements Validator {

    private final UserDetailService userDetailService;

    @Autowired
    public UserValidator(UserDetailService userDetailService) {
        this.userDetailService = userDetailService;
    }


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        try {
            UserDetails findUserByUsername = userDetailService.loadUserByUsername(user.getLogin());
            errors.rejectValue("login", "", "Этот логин уже занят");

        } catch (UsernameNotFoundException ignored) {
            try {
                UserDetails findUserByEmail = userDetailService.loadUserByEmail(user.getEmail());
                errors.rejectValue("email", "", "Эта почта уже занята");
            } catch (UsernameNotFoundException ignored2) {
                return;
            }
        }
    }
}
