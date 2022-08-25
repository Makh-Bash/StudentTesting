package ru.bashirov.studenttesting.models;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "decisions")
public final class Decision {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "time_of_decision")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfDecision;

    @Column(name = "count_of_right_answers")
    private int countOfRightAnswers;

    @Column(name = "count_of_all_answers")
    private int countOfAllAnswers;

    @ManyToOne
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private User person;

    @ManyToOne
    @JoinColumn(name = "tests_id", referencedColumnName = "id")
    private Test test;

    public Decision(Date timeOfDecision, int countOfRightAnswers, int countOfAllAnswers, User person, Test test) {
        this.timeOfDecision = timeOfDecision;
        this.countOfRightAnswers = countOfRightAnswers;
        this.countOfAllAnswers = countOfAllAnswers;
        this.person = person;
        this.test = test;
    }

    public Decision() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getTimeOfDecision() {
        return timeOfDecision;
    }

    public void setTimeOfDecision(Date timeOfDecision) {
        this.timeOfDecision = timeOfDecision;
    }

    public int getCountOfRightAnswers() {
        return countOfRightAnswers;
    }

    public void setCountOfRightAnswers(int countOfRightAnswers) {
        this.countOfRightAnswers = countOfRightAnswers;
    }

    public int getCountOfAllAnswers() {
        return countOfAllAnswers;
    }

    public void setCountOfAllAnswers(int countOfAllAnswers) {
        this.countOfAllAnswers = countOfAllAnswers;
    }

    public User getPerson() {
        return person;
    }

    public void setPerson(User person) {
        this.person = person;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }
}
