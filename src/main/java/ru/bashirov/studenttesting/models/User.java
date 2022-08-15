package ru.bashirov.studenttesting.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    @NotBlank(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно содержать от 2 до 30 символов")
    @Pattern(regexp = "^[А-Яа-я]+$", message = "Имя должно быть на русском языке")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "Фамилия не должна быть пустой")
    @Size(min = 2, max = 30, message = "Фамилия должна содержать от 2 до 30 символов")
    @Pattern(regexp = "^[А-Яа-я]+$", message = "Фамилия должна быть на русском языке")
    private String lastName;

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
    @Size(min = 5, max = 30, message = "Пароль должен содержать от 5 до 30 символов")
    @Pattern(regexp = "^\\w+$", message = "Пароль может содержать только цифры и буквы латинского алфавита")
    private String password;

    @Column(name = "year_of_birth")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date yearOfBirth;

    @Column(name = "time_of_registration")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfRegistration;

    @Column(name = "role")
    private String role;

    @OneToMany(mappedBy = "owner")
    private List<Test> tests;

    public User(String first_name, String last_name, String login, String email, String password, Date yearOfBirth) {
        this.firstName = first_name;
        this.lastName = last_name;
        this.login = login;
        this.email = email;
        this.password = password;
        this.yearOfBirth = yearOfBirth;
    }

    public User() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public Date getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(Date yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public Date getTimeOfRegistration() {
        return timeOfRegistration;
    }

    public void setTimeOfRegistration(Date timeOfRegistration) {
        this.timeOfRegistration = timeOfRegistration;
    }
}
