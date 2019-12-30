package ru.university.web.rest;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ru.university.model.Student;
import ru.university.service.StudentService;

@Controller
public class StudentRestController extends UserBaseRestController<Student> {

    public StudentRestController(@Qualifier("studentService") StudentService service) {
        super(service);
    }

//    protected final Logger log = LoggerFactory.getLogger(getClass());
//
//    private final StudentService service;
//
//    public StudentRestController(StudentService service) {
//        this.service = service;
//    }
//
//    public Collection<Student> getAll() {
//        log.info("getAll");
//        return service.getAll();
//    }
//
//    public Student get(int id) {
//        log.info("get {}", id);
//        return service.get(id);
//    }
//
//    public Student create(Student student) {
//        log.info("create {}", student);
//        checkNew(student);
//        return service.create(student);
//    }
//
//    public void delete(int id) {
//        log.info("delete {}", id);
//        service.delete(id);
//    }
//
//    public void update(Student student, int id) {
//        log.info("update {} with id={}", student, id);
//        assureIdConsistent(student, id);
//        service.update(student);
//    }
//
//    public Student getByMail(String email) {
//        log.info("getByEmail {}", email);
//        return service.getByEmail(email);
//    }
}
