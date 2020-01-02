package ru.university.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "students")
public class Student extends User {


    @Column(name = "record_number", nullable = false, unique = true)
    @NotNull
    @Size(max = 9)
    private Integer recordNumber;

    @Column(name = "average_rating", nullable = false)
    @NotNull
    @Size(max = 5)
    private float averageRating;


    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "students")
    private List<UniversityCourse> courses;

    public Student() {
    }


    public Student(Integer id, String name, String email, String password, String address, boolean enabled, Integer recordNumber, float averageRating, Set<Role> roles) {
        super(id, name, email, password, address, enabled,new Date(), roles);
        this.recordNumber = recordNumber;
        this.averageRating = averageRating;
    }


    public Integer getRecordNumber() {
        return recordNumber;
    }

    public void setRecordNumber(Integer recordNumber) {
        this.recordNumber = recordNumber;
    }

    public float getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(float averageRating) {
        this.averageRating = averageRating;
    }

    public List<UniversityCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<UniversityCourse> courses) {
        this.courses = courses;
    }


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name +
                ", email='" + email +
                ", address=" + address +
                ", recordNumber=" + recordNumber +
                ", averageRating=" + averageRating +
                '}';
    }
}
