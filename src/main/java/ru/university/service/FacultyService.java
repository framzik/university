package ru.university.service;

import org.springframework.stereotype.Service;
import ru.university.model.Faculty;
import ru.university.model.UniversityCourse;
import ru.university.repository.FacultyRepository;
import ru.university.util.exception.NotFoundException;

import java.util.Collection;

import static ru.university.util.ValidationUtil.checkNotFound;
import static ru.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class FacultyService {

    private final FacultyRepository repository;

    public FacultyService(FacultyRepository repository) {
        this.repository = repository;
    }

    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    public Faculty get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    public Faculty getByName(String name) throws NotFoundException{
        return checkNotFound(repository.getByName(name), "name="+ name);
    }

    public Collection<Faculty> getAll() {
        return repository.getAll();
    }

    public void update(Faculty ufaculty) throws NotFoundException {
        checkNotFoundWithId(repository.save(ufaculty), ufaculty.getId());
    }
}
