package ru.university.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import ru.university.model.Faculty;
import ru.university.service.FacultyService;

import java.util.Collection;

import static ru.university.util.ValidationUtil.assureIdConsistent;
import static ru.university.util.ValidationUtil.checkNew;

@Controller
public class FacultyRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final FacultyService service;

    public FacultyRestController(FacultyService service) {
        this.service = service;
    }

    public Collection<Faculty> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Faculty get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Faculty create(Faculty faculty) {
        log.info("create {}", faculty);
        checkNew(faculty);
        return service.create(faculty);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Faculty faculty, int id) {
        log.info("update {} with id={}", faculty, id);
        assureIdConsistent(faculty, id);
        service.update(faculty);
    }

}
