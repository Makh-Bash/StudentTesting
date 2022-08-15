package ru.bashirov.studenttesting.models;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "questions")
public final class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @Size(min = 5, max = 1000, message = "Вопрос должен содержать от 5 до 1000 символов")
    @NotBlank(message = "Поле с вопросом не должно быть пустым")
    private String title;

    @Column(name = "number_of_question")
    private int number_of_question;

    @Column(name = "correct_answer_id")
    @Min(value = 1, message = "Минимальный номер ответа должен быть не меньше 1")
    @Max(value = 4, message = "Максимальный номер ответа должен быть не больше 4")
    private int correctAnswerId;


    /**
     * TODO Попробовать поменять на список из вопросов
     */
    @Column(name = "first_answer")
    @NotBlank(message = "Поле с вопросом не должно быть пустым")
    @Size(min = 1, max = 100, message = "Поле с ответом должно содержать от 1 до 100 символов")
    private String firstAnswer;

    @Column(name = "second_answer")
    @NotBlank(message = "Поле с вопросом не должно быть пустым")
    @Size(min = 1, max = 100, message = "Поле с ответом должно содержать от 1 до 100 символов")
    private String secondAnswer;

    @Column(name = "third_answer")
    @NotBlank(message = "Поле с вопросом не должно быть пустым")
    @Size(min = 1, max = 100, message = "Поле с ответом должно содержать от 1 до 100 символов")
    private String thirdAnswer;

    @Column(name = "fourth_answer")
    @NotBlank(message = "Поле с вопросом не должно быть пустым")
    @Size(min = 1, max = 100, message = "Поле с ответом должно содержать от 1 до 100 символов")
    private String fourthAnswer;

    @ManyToOne
    @JoinColumn(name = "tests_id", referencedColumnName = "id")
    private Test test;

    public Question(String title, int number_of_question, int correctAnswerId, String firstAnswer, String secondAnswer, String thirdAnswer, String fourthAnswer, Test test) {
        this.title = title;
        this.number_of_question = number_of_question;
        this.correctAnswerId = correctAnswerId;
        this.firstAnswer = firstAnswer;
        this.secondAnswer = secondAnswer;
        this.thirdAnswer = thirdAnswer;
        this.fourthAnswer = fourthAnswer;
        this.test = test;
    }

    public Question() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumber_of_question() {
        return number_of_question;
    }

    public void setNumber_of_question(int number_of_question) {
        this.number_of_question = number_of_question;
    }

    public int getCorrectAnswerId() {
        return correctAnswerId;
    }

    public void setCorrectAnswerId(int correctAnswerId) {
        this.correctAnswerId = correctAnswerId;
    }

    public String getFirstAnswer() {
        return firstAnswer;
    }

    public void setFirstAnswer(String firstAnswer) {
        this.firstAnswer = firstAnswer;
    }

    public String getSecondAnswer() {
        return secondAnswer;
    }

    public void setSecondAnswer(String secondAnswer) {
        this.secondAnswer = secondAnswer;
    }

    public String getThirdAnswer() {
        return thirdAnswer;
    }

    public void setThirdAnswer(String thirdAnswer) {
        this.thirdAnswer = thirdAnswer;
    }

    public String getFourthAnswer() {
        return fourthAnswer;
    }

    public void setFourthAnswer(String fourthAnswer) {
        this.fourthAnswer = fourthAnswer;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }


}
