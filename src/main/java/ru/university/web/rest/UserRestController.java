package ru.university.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.university.model.Student;
import ru.university.model.User;
import ru.university.service.UserService;

import java.util.Collection;

import static ru.university.util.ValidationUtil.assureIdConsistent;
import static ru.university.util.ValidationUtil.checkNew;

public class UserRestController<T extends User> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    private final UserService<T> service;

    public UserRestController(UserService<T> service) {
        this.service = service;
    }

    public Collection<T> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public T get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public T create(T user) {
        log.info("create {}", user);
        checkNew(user);
        return service.create(user);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(T user, int id) {
        log.info("update {} with id={}", user, id);
        assureIdConsistent(user, id);
        service.update(user);
    }

    public T getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
