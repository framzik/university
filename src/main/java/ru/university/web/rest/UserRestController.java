package ru.university.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.university.model.User;
import ru.university.service.UserService;

import java.util.Collection;

import static ru.university.util.ValidationUtil.assureIdConsistent;
import static ru.university.util.ValidationUtil.checkNew;

@Controller
public class UserRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService service;

    public UserRestController(UserService service) {
        this.service = service;
    }


    public Collection<User> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public User get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public User create(User entity) {
        log.info("create {}", entity);
        checkNew(entity);
        return service.create(entity);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(User entity, int id) {
        log.info("update {} with id={}", entity, id);
        assureIdConsistent(entity, id);
        service.update(entity);
    }

    public User getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
