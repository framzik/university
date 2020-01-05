package ru.university.repository.inmemory;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.university.UserTestData;
import ru.university.model.Course;
import ru.university.repository.CourseRepository;
import ru.university.util.Util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static ru.university.CourseTestData.UNIVERSITY_COURSES;

@Repository
public class InMemoryCourseRepository implements CourseRepository {

    private Map<Integer, InMemoryBaseRepository<Course>> studentCourseMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        InMemoryBaseRepository<Course> userCourse = new InMemoryBaseRepository<>();
        studentCourseMap.put(UserTestData.STUDENT_ID, userCourse);
        UNIVERSITY_COURSES.forEach(course->userCourse.map.put(course.getId(),course));
    }


    @Override
    public Course save(Course course, int userId) {
        Objects.requireNonNull(course, "course must not be null");
        InMemoryBaseRepository<Course> universityCourses = studentCourseMap.computeIfAbsent(userId,uid->new InMemoryBaseRepository<>());
        return universityCourses.save(course);
    }

    @Override
    public boolean delete(int id, int userId) {
        InMemoryBaseRepository<Course> universityCourses = studentCourseMap.get(userId);
        return universityCourses != null && universityCourses.delete(id) ;
    }

    @Override
    public Course get(int id, int userId) {
        InMemoryBaseRepository<Course> universityCourses = studentCourseMap.get(userId);
        return universityCourses == null ? null : universityCourses.get(id);
    }

    @Override
    public Course getByName(String name, int userId) {
        Objects.requireNonNull(name, "name must not be null");
        InMemoryBaseRepository<Course> universityCourses = studentCourseMap.get(userId);
        return universityCourses.getCollection().stream()
                .filter(course -> name.equals(course.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Course> getAll(int userId) {
        return getAllFiltered(userId, course -> true);
    }

    private List<Course> getAllFiltered(int userId, Predicate<Course> filter) {
        InMemoryBaseRepository<Course> universityCourses = studentCourseMap.get(userId);

        return universityCourses==null ? Collections.emptyList() :
                universityCourses.getCollection().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(Course::getName).reversed())
                        .collect(Collectors.toList());
    }

    @Override
    public List<Course> getBetweenCost(float startCost, float endCost, int userId) {
        return getAllFiltered(userId, course -> Util.isBetweenInclusive(course.getCost(),startCost,endCost));
    }
}
