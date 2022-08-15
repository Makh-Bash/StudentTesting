package ru.bashirov.studenttesting.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    private String title;

    @Column(name = "category")
    private String category;

    @Column(name = "count_of_questions")
    private int countOfQuestions;

    @Column(name = "count_of_decisions")
    private int countOfDecisions;

    @Column(name = "date_of_creation")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date dateOfCreation;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User owner;

    @OneToMany(mappedBy = "test")
    private List<Question> questions;


    public Test(String title, String category, int countOfQuestions, Date dateOfCreation, User owner) {
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
}
