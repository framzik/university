package ru.university.repository.inmemory;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.university.model.Course;
import ru.university.repository.CourseRepository;
import ru.university.util.Util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Repository
public class InMemoryCourseRepository implements CourseRepository {

    private Map<Integer, Map<Integer, Course>> studentCourseMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);




    @Override
    public Course save(Course course, int userId) {
        Objects.requireNonNull(course, "course must not be null");
        Map<Integer, Course> universityCourses = studentCourseMap.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (course.isNew()) {
            course.setId(counter.incrementAndGet());
            universityCourses.put(course.getId(), course);
            return course;
        }
        return universityCourses.computeIfPresent(course.getId(), (id, oldUniCources) -> course);
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Course> universityCourses = studentCourseMap.get(userId);
        return universityCourses != null && universityCourses.remove(id) != null;
    }

    @Override
    public Course get(int id, int userId) {
        Map<Integer, Course> universityCourses = studentCourseMap.get(userId);
        return universityCourses == null ? null : universityCourses.get(id);
    }

    @Override
    public Course getByName(String name, int userId) {
        Objects.requireNonNull(name, "name must not be null");
        Map<Integer, Course> universityCourses = studentCourseMap.get(userId);
        return universityCourses.values().stream()
                .filter(course -> name.equals(course.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Course> getAll(int userId) {
        return getAllFiltered(userId, course -> true);
    }

    private List<Course> getAllFiltered(int userId, Predicate<Course> filter) {
        Map<Integer, Course> universityCourses = studentCourseMap.get(userId);

        return CollectionUtils.isEmpty(universityCourses) ? Collections.emptyList() :
                universityCourses.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Course::getName).reversed())
                        .collect(Collectors.toList());
    }

    @Override
    public List<Course> getBetweenCost(float startCost, float endCost, int userId) {
        return getAllFiltered(userId, course -> Util.isBetweenInclusive(course.getCost(),startCost,endCost));
    }
}
