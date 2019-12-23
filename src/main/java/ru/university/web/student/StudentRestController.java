package ru.university.web.student;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.university.model.Student;
import ru.university.service.StudentService;

import java.util.Collection;
import java.util.List;

import static ru.university.util.ValidationUtil.assureIdConsistent;
import static ru.university.util.ValidationUtil.checkNew;


public class StudentRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private StudentService service;

    public Collection<Student> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Student get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Student create(Student  student) {
        log.info("create {}", student);
        checkNew(student);
        return service.create(student);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Student student, int id) {
        log.info("update {} with id={}", student, id);
        assureIdConsistent(student, id);
        service.update(student);
    }

    public Student getByMail(String email) {
        log.info("getByEmail {}", email);
        return service.getByEmail(email);
    }
}