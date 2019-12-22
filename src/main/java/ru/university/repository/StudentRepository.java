package ru.university.repository;

import ru.university.model.Student;

import java.util.List;

public interface StudentRepository {
    Student save(Student student);

    boolean delete(int id);

    Student get(int id);

    Student getByEmail(String email);

    List<Student> getAll();
}
