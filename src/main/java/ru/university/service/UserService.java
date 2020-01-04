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
public class UserService {
    @Autowired
    private UserRepository repository;


    public User create(User entity) {
        return repository.save(entity);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public  User get(int id) throws NotFoundException {
        return  checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public Collection<User> getAll() {
        return repository.getAll();
    }

    public   void update(User entity) throws NotFoundException {
        checkNotFoundWithId(repository.save(entity), entity.getId());
    }
}
