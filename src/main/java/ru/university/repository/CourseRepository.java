package ru.university.repository;

import org.springframework.lang.Nullable;
import ru.university.model.Course;

import java.util.List;

public interface CourseRepository {
    Course save(Course course, int userId);

    boolean delete(int id, int userId);

    Course get(int id, int userId);

    Course getByName(String name, int userId);

    List<Course> getAll(int userId);

    List<Course> getBetweenCost(@Nullable float startCost, @Nullable float endCost, int userId);
}
