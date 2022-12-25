package ru.bashirov.studenttesting.models;

import javax.persistence.*;

@Entity
@Table(name = "groups")
public class Group {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "number")
    private int number;

    @Column(name = "year_of_admission")
    private int yearOfAddmission;

    public Group() {
    }

    public Group(int number, int yearOfAdmission) {
        this.number = number;
        this.yearOfAddmission = yearOfAdmission;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getYearOfAddmission() {
        return yearOfAddmission;
    }

    public void setYearOfAddmission(int yearOfAddmission) {
        this.yearOfAddmission = yearOfAddmission;
    }
}
