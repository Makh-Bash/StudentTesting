package ru.bashirov.studenttesting.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "login")
    @NotBlank(message = "Логин не должен быть пустым")
    @Size(min = 5, max = 30, message = "Логин должен содержать от 5 до 30 символов")
    @Pattern(regexp = "^\\w+$", message = "Логин должен содержать только цифры и буквы латинского алфавита")
    private String login;

    @Column(name = "email")
    @NotBlank(message = "Email не должен быть пустым")
    @Email(message = "Email должен быть корректным")
    private String email;

    @Column(name = "password")
    @NotBlank(message = "Пароль не должен быть пустым")
    @Size(min = 5, max = 100, message = "Пароль должен содержать от 5 до 30 символов")
//    @Pattern(regexp = "^\\w+$", message = "Пароль может содержать только цифры и буквы латинского алфавита")
    private String password;

    @Column(name = "role")
    private String role;


    public User(String login, String email, String password) {
        this.login = login;
        this.email = email;
        this.password = password;

    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
