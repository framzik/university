package ru.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.model.User;
import ru.university.repository.UserRepository;
import ru.university.util.exception.NotFoundException;

import java.util.Collection;

import static ru.university.util.ValidationUtil.checkNotFound;
import static ru.university.util.ValidationUtil.checkNotFoundWithId;
@Service
public class UserService<T extends User> {
    private final UserRepository<T> repository;

    public UserService(UserRepository<T> repository) {
        this.repository = repository;
    }

    public T create(T user) {
        return repository.save(user);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public  T get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public T getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public   Collection<T> getAll() {
        return repository.getAll();
    }

    public   void update(T user) throws NotFoundException {
        checkNotFoundWithId(repository.save(user), user.getId());
    }
}
