package ru.university.service;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import ru.university.model.Student;
import ru.university.model.User;
import ru.university.repository.UserRepository;
import ru.university.util.exception.NotFoundException;

import java.util.Collection;
@Service
public class StudentService extends UserService<Student> {
    public StudentService(UserRepository<Student> repository) {
        super(repository);
    }

    @Override
    public Student create(Student user) {
        return super.create(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        super.delete(id);
    }

    @Override
    public Student get(int id) throws NotFoundException {
        return super.get(id);
    }

    @Override
    public Student getByEmail(String email) throws NotFoundException {
        return super.getByEmail(email);
    }

    @Override
    public Collection<Student> getAll() {
        return super.getAll();
    }

    @Override
    public void update(Student user) throws NotFoundException {
        super.update(user);
    }
}
