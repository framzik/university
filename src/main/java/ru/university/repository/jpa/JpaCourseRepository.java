package ru.university.repository.jpa;

import org.springframework.stereotype.Repository;
import ru.university.model.Course;
import ru.university.repository.CourseRepository;

import java.util.List;
@Repository
public class JpaCourseRepository implements CourseRepository {
    @Override
    public Course save(Course course, int userId) {
        return null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Course get(int id, int userId) {
        return null;
    }

    @Override
    public Course getByName(String name, int userId) {
        return null;
    }

    @Override
    public List<Course> getAll(int userId) {
        return null;
    }

    @Override
    public List<Course> getBetweenCost(float startCost, float endCost, int userId) {
        return null;
    }
}
