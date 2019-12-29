package ru.university.repository;

import ru.university.model.AbstractNamedEntity;
import ru.university.model.User;

import java.util.Collection;

public interface UserRepository<T extends User> {
    T save( T  user);

    boolean delete(int id);

     T get(int id);

     T getByEmail(String email);

     Collection<T>  getAll();
}
