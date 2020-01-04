package ru.university.repository;

import org.springframework.stereotype.Repository;
import ru.university.model.User;

import java.util.Collection;
@Repository
public interface UserRepository<T extends User> {
    T save(T entity);


    boolean delete(int id);


    T get(int id);


    T getByEmail(String email);


    Collection<T> getAll();
}
