package ru.university.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.university.model.UniversityCourse;
import ru.university.repository.UniversityCourseRepository;
import ru.university.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

import static ru.university.util.ValidationUtil.checkNotFound;
import static ru.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class UniversityCourseService {
    private final UniversityCourseRepository repository;

    public UniversityCourseService(UniversityCourseRepository repository) {
        this.repository = repository;
    }

    public void delete(int id, int facultyId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, facultyId), id);
    }

    public UniversityCourse get(int id, int facultyId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id,facultyId), id);
    }

    public UniversityCourse getByName(String name, int facultyId) throws NotFoundException {
        return checkNotFound(repository.getByName(name, facultyId), "name=" + name);
    }

    public List<UniversityCourse> getAll(int facultyId) {
        return repository.getAll(facultyId);
    }

    public void update(UniversityCourse universityCourse, int facultyId) throws NotFoundException {
        checkNotFoundWithId(repository.save(universityCourse,facultyId), universityCourse.getId());
    }

    public List<UniversityCourse> getBetweenCost(@Nullable float startCost, @Nullable float endCost, int facultyId) {
        return repository.getBetweenCost(startCost,endCost,facultyId);
    }


    public UniversityCourse create(UniversityCourse course, int facultyId) {
       return repository.save(course,facultyId);
    }
}
