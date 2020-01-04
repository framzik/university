package ru.university.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.university.model.Student;
import ru.university.model.User;
import ru.university.service.UserBaseService;

import java.util.Collection;

import static ru.university.util.ValidationUtil.assureIdConsistent;
import static ru.university.util.ValidationUtil.checkNew;
@Controller
public class UserRestController<T extends User> {
    protected final Logger log = LoggerFactory.getLogger(getClass());
@Autowired
    private final UserBaseService<T> service;

    public UserRestController(UserBaseService<T> service) {
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

    public T create(T entity) {
        log.info("create {}", entity);
        checkNew(entity);
        return service.create(entity);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(T entity, int id) {
        log.info("update {} with id={}", entity, id);
        assureIdConsistent(entity, id);
        service.update(entity);
    }

    public T getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}
