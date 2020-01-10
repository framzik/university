package ru.university.repository;

import org.springframework.stereotype.Repository;
import ru.university.model.Course;
import ru.university.model.User;

import java.util.Collection;

@Repository
public interface UserRepository {
    User save(User entity);


    boolean delete(int id);


    User get(int id);


    User getByEmail(String email);


    Collection<User> getAll();

    default User getWithCourse(int id) {
        throw new UnsupportedOperationException();
    }
}
