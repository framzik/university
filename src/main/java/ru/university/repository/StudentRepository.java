package ru.university.repository;

import org.springframework.stereotype.Repository;
import ru.university.model.Student;
import ru.university.model.User;

import java.util.Collection;

public interface StudentRepository extends UserRepository<Student> {
    @Override
    Student save(Student user);

    @Override
    boolean delete(int id);

    @Override
    Student get(int id);

    @Override
    Student getByEmail(String email);

    @Override
    Collection<Student> getAll();
}
