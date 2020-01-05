package ru.university.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Entity
@Table(name = "university_courses")
public class Course extends AbstractNamedEntity {

    @NotNull
    @Size(max = 7)
    @Column(name = "number",unique = true, nullable = false)
    private int number;

    @NotNull
    @Size(max = 9)
    @Column(name = "cost",unique = true, nullable = false)
    private float cost;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public Course() {
    }

    public Course(Integer id, String name, int number, float cost) {
        super(id,name);
        this.number = number;
        this.cost = cost;
    }
    public Course(String name, int number, float cost) {
        this.id=null;
        this.name = name;
        this.number = number;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "UniversityCourse{" +
                "name='" + name + '\'' +
                ", number=" + number +
                ", cost=" + cost +
                ", id=" + id +
                '}';
    }
}
