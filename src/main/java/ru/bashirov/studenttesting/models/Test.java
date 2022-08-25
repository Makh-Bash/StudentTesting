package ru.bashirov.studenttesting.models;

import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tests")
public final class Test {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    @NotBlank(message = "Название теста не может быть пустым")
    @Size(min = 4, max = 100, message = "Название может содержать от 4 до 100 символов")
    private String title;

    @Column(name = "count_of_questions")
    @Min(value = 2, message = "Количество вопросов должно быть не меньше 2")
    @Max(value = 100, message = "Количество вопросов должно быть не больше 100")
    private int countOfQuestions;

    @Column(name = "count_of_decisions")
    private int countOfDecisions;

    @Column(name = "date_of_creation")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateOfCreation;
    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private TestCategory category;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User owner;

    @OneToMany(mappedBy = "test")
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private List<Question> questions;

    @Transient
    private int countOfCurrentUserDecisions;

    @Transient
    private int bestDecisionOfCurrentUser;


    public Test(String title, TestCategory category, int countOfQuestions, Date dateOfCreation, User owner) {
        this.title = title;
        this.category = category;
        this.countOfQuestions = countOfQuestions;
        this.dateOfCreation = dateOfCreation;
        this.owner = owner;
    }

    public Test() {}

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

    public int getCountOfQuestions() {
        return countOfQuestions;
    }

    public void setCountOfQuestions(int countOfQuestions) {
        this.countOfQuestions = countOfQuestions;
    }

    public int getCountOfDecisions() {
        return countOfDecisions;
    }

    public void setCountOfDecisions(int countOfDecisions) {
        this.countOfDecisions = countOfDecisions;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public TestCategory getCategory() {
        return category;
    }

    public void setCategory(TestCategory testCategory) {
        this.category = testCategory;
    }

    public int getCountOfCurrentUserDecisions() {
        return countOfCurrentUserDecisions;
    }

    public void setCountOfCurrentUserDecisions(int countOfCurrentUserDecisions) {
        this.countOfCurrentUserDecisions = countOfCurrentUserDecisions;
    }

    public int getBestDecisionOfCurrentUser() {
        return bestDecisionOfCurrentUser;
    }

    public void setBestDecisionOfCurrentUser(int bestDecisionOfCurrentUser) {
        this.bestDecisionOfCurrentUser = bestDecisionOfCurrentUser;
    }
}
