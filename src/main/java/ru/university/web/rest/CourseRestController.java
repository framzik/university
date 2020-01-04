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
        int facultyId = SecurityUtil.authUserId();
        log.info("getBetween cost({} - {}) for faculty {}", startCost, endCost, facultyId);
        return service.getBetweenCost(startCost, endCost, facultyId);
    }

    public Course get(int id) {
        int facultyId = SecurityUtil.authUserId();
        log.info("get course {} for faculty {}", id, facultyId);
        return service.get(id, facultyId);
    }

    public void delete(int id) {
        int facultyId = SecurityUtil.authUserId();
        log.info("delete meal {} for faculty {}", id, facultyId);
        service.delete(id, facultyId);
    }

    public List<Course> getAll() {
        int facultyId = SecurityUtil.authUserId();
        log.info("getAll for faculty {}", facultyId);
        return service.getAll(facultyId);
    }

    public Course create(Course course) {
        int facultyId = SecurityUtil.authUserId();
        checkNew(course);
        log.info("create {} for faculty {}", course, facultyId);
        return service.create(course, facultyId);
    }

    public void update(Course course, int id) {
        int facultyId = SecurityUtil.authUserId();
        assureIdConsistent(course, id);
        log.info("update {} for faculty {}", course, facultyId);
        service.update(course, facultyId);
    }


}
