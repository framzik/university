package ru.university.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "students")
public class Student extends AbstractEntity {
    @Column(name = "email", nullable = false, unique = true)
    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

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

    public Student(Integer id, String name, String address, String email, Integer recordNumber, float averageRating) {
        super(id, name, address);
        this.email = email;
        this.recordNumber = recordNumber;
        this.averageRating = averageRating;
    }

    public Student(String name, String address, String email, Integer recordNumber, float averageRating) {
        super(name, address);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public void setAverageRating(long averageRating) {
        this.averageRating = averageRating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Student student = (Student) o;

        if (averageRating != student.averageRating) return false;
        if (!email.equals(student.email)) return false;
        return recordNumber.equals(student.recordNumber);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (recordNumber != null ? recordNumber.hashCode() : 0);
        result = 31 * result + (averageRating != +0.0f ? Float.floatToIntBits(averageRating) : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Student{" +
                "email='" + email + '\'' +
                ", recordNumber=" + recordNumber +
                ", averageRating=" + averageRating +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", id=" + id +
                '}';
    }
}
