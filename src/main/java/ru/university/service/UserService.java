package ru.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.university.model.User;
import ru.university.repository.UserRepository;

import java.util.Collection;

import static ru.university.util.ValidationUtil.checkNotFound;
import static ru.university.util.ValidationUtil.checkNotFoundWithId;
@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public User create(User entity) {
        Assert.notNull(entity, "user must not be null");
        return repository.save(entity);
    }

    public void delete(int id)  {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public  User get(int id)  {
        return  checkNotFoundWithId(repository.get(id), id);
    }

    public User getByEmail(String email)  {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public Collection<User> getAll() {
        return repository.getAll();
    }

    public   void update(User entity)  {
        Assert.notNull(entity, "user must not be null");
        checkNotFoundWithId(repository.save(entity), entity.getId());
    }
}
