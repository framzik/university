package ru.university.web.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.university.model.Course;
import ru.university.service.CourseService;
import ru.university.web.SecurityUtil;

import java.util.List;

import static ru.university.util.ValidationUtil.assureIdConsistent;
import static ru.university.util.ValidationUtil.checkNew;

@Controller
public class CourseRestController {

    private final CourseService service;

    public CourseRestController(CourseService service) {
        this.service = service;
    }

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

    public void update(Course course, int id) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(course, id);
        log.info("update {} for user {}", course, userId);
        service.update(course, userId);
    }


}
