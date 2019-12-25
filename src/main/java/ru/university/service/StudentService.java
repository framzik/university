package ru.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.model.Student;
import ru.university.repository.StudentRepository;
import ru.university.util.exception.NotFoundException;

import java.util.Collection;

import static ru.university.util.ValidationUtil.checkNotFound;
import static ru.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class StudentService {
    @Autowired
    private StudentRepository repository;



    public Student create(Student student) {
        return repository.save(student);
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Student get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Student getByEmail(String email) throws NotFoundException {
        return checkNotFound(repository.getByEmail(email), "email=" + email);
    }

    public Collection<Student> getAll() {
        return repository.getAll();
    }

    public void update(Student student) throws NotFoundException {
        checkNotFoundWithId(repository.save(student), student.getId());
    }


}
