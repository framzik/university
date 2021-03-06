package ru.university.web.course;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import ru.university.model.Course;
import ru.university.service.CourseService;
import ru.university.to.CourseTo;
import ru.university.util.Util;
import ru.university.web.SecurityUtil;

import java.util.List;

import static ru.university.util.ValidationUtil.assureIdConsistent;
import static ru.university.util.ValidationUtil.checkNew;

public abstract class AbstractCourseController {

    @Autowired
    private CourseService service;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public List<Course> getBetween(@Nullable float startCost, @Nullable float endCost) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween cost({} - {}) for user {}", startCost, endCost, userId);
        return service.getBetweenCost(startCost, endCost, userId);
    }

    public Course get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get course {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete course {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<Course> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return service.getAll(userId);
    }

    public Course create(Course course) {
        int userId = SecurityUtil.authUserId();
        checkNew(course);
        log.info("create {} for user {}", course, userId);
        return service.create(course, userId);
    }
    public Course create(CourseTo courseTo) {
        int userId = SecurityUtil.authUserId();
        checkNew(courseTo);
        log.info("create {} for user {}", courseTo, userId);
        return service.create(Util.createNewCourseFromTo(courseTo), userId);
    }

    public void update(Course course, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(course, id);
        log.info("update {} for user {}", course, userId);
        service.update(course, userId);
    }

    public void update(CourseTo courseTo, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(courseTo, id);
        log.info("update {} with id={}", courseTo, id);
        assureIdConsistent(courseTo, id);
        service.update(courseTo,userId);
    }
}
