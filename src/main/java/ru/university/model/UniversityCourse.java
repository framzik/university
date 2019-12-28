package ru.university.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
@Entity
@Table(name = "university_course")
public class UniversityCourse extends AbstractNamedEntity {

    @NotNull
    @Size(max = 7)
    @Column(name = "number",unique = true, nullable = false)
    private int number;

    @NotNull
    @Size(max = 9)
    @Column(name = "cost",unique = true, nullable = false)
    private float cost;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "courses")
    private List<Student> students;

    public UniversityCourse() {
    }

    public UniversityCourse(Integer id, String name, int number, float cost) {
        super(id,name);
        this.number = number;
        this.cost = cost;
    }
    public UniversityCourse(String name, int number, float cost) {
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
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
