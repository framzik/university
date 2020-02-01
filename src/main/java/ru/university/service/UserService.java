package ru.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.university.model.User;
import ru.university.repository.UserRepository;
import ru.university.to.UserTo;
import ru.university.util.Util;

import java.util.Collection;
import java.util.List;

import static ru.university.util.ValidationUtil.checkNotFound;
import static ru.university.util.ValidationUtil.checkNotFoundWithId;
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    @CacheEvict(value = "users", allEntries = true)
    public User create(User entity) {
        Assert.notNull(entity, "user must not be null");
        return repository.save(entity);
    }
    @CacheEvict(value = "users", allEntries = true)
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

    @Cacheable("users")
    public List<User> getAll() {
        return repository.getAll();
    }

    @CacheEvict(value = "users", allEntries = true)
    public   void update(User entity)  {
        Assert.notNull(entity, "user must not be null");
        repository.save(entity);
    }
    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void update(UserTo userTo) {
        User user = get(userTo.id());
        repository.save(Util.updateFromTo(user, userTo));
    }

    @CacheEvict(value = "users", allEntries = true)
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
    }

    public User  getWithCourse(int id) {
       return checkNotFoundWithId(repository.getWithCourse(id),id);
    }
}
