package model;

import java.util.List;

public class Student extends AbstractEntity {
    private String email;
    private Integer recordNumber;
    private float averageRating;
    private List<UniversityCourse> courses;

    public Student() {
    }

    public Student(Integer id, String name, String address, String telephone, String email, Integer recordNumber, float averageRating) {
        super(id, name, address, telephone);
        this.email = email;
        this.recordNumber = recordNumber;
        this.averageRating = averageRating;
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
                ", telephone='" + telephone + '\'' +
                ", id=" + id +
                '}';
    }
}
