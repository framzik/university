package ru.university.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.university.model.Course;
import ru.university.repository.CourseRepository;
import ru.university.util.exception.NotFoundException;

import java.util.List;

import static ru.university.util.ValidationUtil.checkNotFound;
import static ru.university.util.ValidationUtil.checkNotFoundWithId;

@Service
public class CourseService {
    private final CourseRepository repository;

    public CourseService(CourseRepository repository) {
        this.repository = repository;
    }

    public void delete(int id, int userId)  {
        checkNotFoundWithId(repository.delete(id, userId), id);
    }

    public Course get(int id, int userId)  {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    public Course getByName(String name, int userId)  {
        return checkNotFound(repository.getByName(name, userId), "name=" + name);
    }

    public List<Course> getAll(int userId) {
        return repository.getAll(userId);
    }

    public void update(Course course, int userId)  {
        checkNotFoundWithId(repository.save(course, userId), course.getId());
    }

    public List<Course> getBetweenCost(@Nullable float startCost, @Nullable float endCost, int userId) {
        return repository.getBetweenCost(startCost, endCost, userId);
    }


    public Course create(Course course, int userId) {
        return repository.save(course, userId);
    }
}
