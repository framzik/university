package ru.university.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.university.CourseTestData;
import ru.university.UserTestData;
import ru.university.model.Course;
import ru.university.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.university.CourseTestData.*;
import static ru.university.UserTestData.PROFESSOR_ID;
import static ru.university.UserTestData.STUDENT_ID;


public class CourseServiceTest extends AbstractServiceTest {

    @Autowired
    CourseService service;

    @Test
    public void delete() {
        service.delete(STUDENT_COURSE_ID + 1, STUDENT_ID);
        assertThrows(NotFoundException.class, () ->
                service.get(STUDENT_COURSE_ID + 1, STUDENT_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(7, STUDENT_ID));
    }

    @Test
    public void get() {
        Course courseByName = service.get(STUDENT_COURSE_ID + 1, STUDENT_ID);
        assertMatch(courseByName, COURSE_2);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(7, STUDENT_ID));
    }

    @Test
    public void getNotFound2() {
        assertThrows(NotFoundException.class, () ->
                service.get(STUDENT_COURSE_ID, PROFESSOR_ID));
    }


    @Test
    public void getByName() {
        Course courseByName = service.getByName(COURSE_2.getName(), STUDENT_ID);
        assertMatch(courseByName, COURSE_2);
    }

    @Test
    public void getByNameNotFound() {
        assertThrows(NotFoundException.class, () -> service.getByName("HELLO WORLD", STUDENT_ID));
    }

    @Test
    public void getAll() {
        List<Course> courseList = service.getAll(STUDENT_ID);
        assertMatch(courseList, COURSE_2, COURSE_3, COURSE_1);
    }

    @Test
    public void update() {
        Course updated = CourseTestData.getUpdated();
        service.update(updated, STUDENT_ID);
        assertMatch(service.get(STUDENT_COURSE_ID, STUDENT_ID), updated);
    }

    @Test
    public void updateNotFound() {
        NotFoundException e = assertThrows(NotFoundException.class, () -> service.update(COURSE_1, PROFESSOR_ID));
        assertEquals(e.getMessage(), "Not found entity with id=" + COURSE_1.getId());
    }

    @Test
    public void getBetween() {
        assertMatch(service.getBetweenCost(15500, 17000, STUDENT_ID), COURSE_2, COURSE_3);
    }

    @Test
    public void create() {
        Course newCourse = CourseTestData.getNew();
        Course created = service.create(newCourse, STUDENT_COURSE_ID);
        newCourse.setId(created.getId());
        assertMatch(newCourse, created);
        assertMatch(service.getAll(STUDENT_COURSE_ID), COURSE_2, created, COURSE_3, COURSE_1);
    }

    @Test
    public void duplicateNumberCreate() {
//        service.create(new Course(null, "Новый Курс", 666, 1200), STUDENT_ID);
        assertThrows(DataIntegrityViolationException.class, () -> service.create(new Course(null, "Новый Курс", 666, 1200), STUDENT_ID));
    }

    @Test
    public void getWithUser() {
        Course studenCourse = service.getWithUser(STUDENT_COURSE_ID + 1, STUDENT_ID);
        assertMatch(studenCourse, COURSE_2);
        UserTestData.assertMatch(studenCourse.getUser(), UserTestData.YAMCHEKOV);
    }

    @Test()
    public void getWithUserNotFound() throws Exception {
        assertThrows(NotFoundException.class, () -> service.getWithUser(1, 12));
    }
}