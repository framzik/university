package ru.university.web.rest;

import org.springframework.stereotype.Controller;
import ru.university.model.Student;
import ru.university.service.StudentService;
import ru.university.service.UserService;

import java.util.Collection;
@Controller
public class StudentRestController extends UserRestController<Student> {

    public StudentRestController(StudentService service) {
        super(service);
    }

    @Override
    public Collection<Student> getAll() {
        return super.getAll();
    }

    @Override
    public Student get(int id) {
        return super.get(id);
    }

    @Override
    public Student create(Student user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public void update(Student student, int id) {
        super.update(student, id);
    }

    @Override
    public Student getByMail(String email) {
        return super.getByMail(email);
    }
}
