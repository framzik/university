package ru.university.repository;

import ru.university.model.Student;

import java.util.Collection;

public interface StudentRepository {
    Student save(Student student);

    boolean delete(int id);

    Student get(int id);

    Student getByEmail(String email);

    Collection<Student> getAll();
}
