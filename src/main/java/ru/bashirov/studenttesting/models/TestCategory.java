package ru.bashirov.studenttesting.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "test_category")
public class TestCategory {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Test> test;

    public TestCategory(String title, List<Test> test) {
        this.title = title;
        this.test = test;
    }

    public TestCategory() {}

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

    public List<Test> getTest() {
        return test;
    }

    public void setTest(List<Test> test) {
        this.test = test;
    }
}
