package ru.university.model;

import java.util.List;

public class Faculty extends AbstractNamedEntity {
    private List<UniversityCourse> courses;

    public Faculty() {
    }

    public Faculty(Integer id, String name, List<UniversityCourse> courses) {
        super(id, name);
        this.courses = courses;
    }

    public Faculty(Integer id, String name) {
        super(id, name);
    }

    public List<UniversityCourse> getCourses() {
        return courses;
    }

    public void setCourses(List<UniversityCourse> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "courses=" + courses +
                ", name='" + name + '\'' +
                ", id=" + id +
                '}';
    }
}
