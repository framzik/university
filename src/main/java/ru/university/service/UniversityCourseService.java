package ru.university.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.university.model.Student;
import ru.university.model.UniversityCourse;
import ru.university.repository.UniversityCourseRepository;
import ru.university.util.exception.NotFoundException;

import java.util.Collection;

import static ru.university.util.ValidationUtil.checkNotFound;
import static ru.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UniversityCourseService {
    private final UniversityCourseRepository repository;

    public UniversityCourseService(UniversityCourseRepository repository) {
        this.repository = repository;
    }

//    public void delete(int id) throws NotFoundException {
//        checkNotFoundWithId(repository.delete(id), id);
//    }
//
//    public UniversityCourse get(int id) throws NotFoundException {
//        return checkNotFoundWithId(repository.get(id), id);
//    }
//
//    public UniversityCourse getByName(String name) throws NotFoundException{
//        return checkNotFound(repository.getByName(name), "name="+ name);
//    }
//
//    public Collection<UniversityCourse> getAll() {
//        return repository.getAll();
//    }
//
//    public void update(UniversityCourse universityCourse) throws NotFoundException {
//        checkNotFoundWithId(repository.save(universityCourse), universityCourse.getId());
//    }
}
