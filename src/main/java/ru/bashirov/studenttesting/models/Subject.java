package ru.bashirov.studenttesting.models;

import javax.persistence.*;

@Entity
@Table(name = "subjects")
public class Subject {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    private String type;

    @OneToOne
    @JoinColumn(name = "teacher", referencedColumnName = "id")
    private Teacher teacher;
}
