package ru.bashirov.studenttesting.models;

import javax.persistence.*;

@Entity
@Table(name = "questions")
public final class Question {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "number_of_question")
    private int number_of_question;

    @Column(name = "correct_answer_id")
    private int correctAnswerId;

    @Column(name = "first_answer")
    private String firstAnswer;

    @Column(name = "second_answer")
    private String secondAnswer;

    @Column(name = "third_answer")
    private String thirdAnswer;

    @Column(name = "fourth_answer")
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
