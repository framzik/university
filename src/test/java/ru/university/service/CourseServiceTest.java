package ru.university.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.university.CourseTestData;
import ru.university.UserTestData;
import ru.university.model.Course;
import ru.university.util.exception.NotFoundException;

import java.util.List;

import static ru.university.CourseTestData.*;
import static ru.university.UserTestData.*;


public class CourseServiceTest extends AbstractServiceTest {

    @Autowired
    CourseService service;

    @Test
    public void delete() {
        service.delete(STUDENT_COURSE_ID + 1, STUDENT_ID);
        thrown.expect(NotFoundException.class);
        service.get(STUDENT_COURSE_ID + 1, STUDENT_ID);
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(7, STUDENT_ID);
    }

    @Test
    public void get() {
        Course courseByName = service.get(STUDENT_COURSE_ID + 1, STUDENT_ID);
        assertMatch(courseByName, COURSE_2);
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(7, STUDENT_ID);
    }

    @Test
    public void getNotFound2() {
        thrown.expect(NotFoundException.class);
        service.get(STUDENT_COURSE_ID, PROFESSOR_ID);
    }


    @Test
    public void getByName() {
        Course courseByName = service.getByName(COURSE_2.getName(), STUDENT_ID);
        assertMatch(courseByName, COURSE_2);
    }

    @Test
    public void getByNameNotFound() {
        thrown.expect(NotFoundException.class);
        Course course = service.getByName("HELLO WORLD", STUDENT_ID);
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
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + COURSE_1.getId());
        service.update(COURSE_1, PROFESSOR_ID);
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

    @Test(expected = DataIntegrityViolationException.class)
    public void duplicateNumberCreate() {
        service.create(new Course(null, "Новый Курс", 666, 1200), STUDENT_ID);
    }

    @Test
    public void getWithUser() {
        Course studenCourse = service.getWithUser(STUDENT_COURSE_ID + 1, STUDENT_ID);
        assertMatch(studenCourse, COURSE_2);
        UserTestData.assertMatch(studenCourse.getUser(), UserTestData.YAMCHEKOV);
    }

    @Test(expected = NotFoundException.class)
    public void getWithUserNotFound() throws Exception {
        service.getWithUser(1, 12);
    }
}