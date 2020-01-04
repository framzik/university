package ru.university.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.university.UserTestData;
import ru.university.model.Course;
import ru.university.model.Role;
import ru.university.model.User;
import ru.university.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.university.CourseTestData.*;
import static ru.university.UserTestData.BELYALOV;
import static ru.university.UserTestData.STUDENT_ID;
import static ru.university.CourseTestData.assertMatch;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql", config = @SqlConfig(encoding = "UTF-8"))
public class CourseServiceTest {
    @Autowired
    CourseService service;


    @Test
    public void delete() {
        service.delete(COURSE_2.getId(),STUDENT_ID);
        assertMatch(service.getAll(STUDENT_ID), COURSE_3, COURSE_1);
    }
    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(7,STUDENT_ID);
    }

    @Test
    public void get() {
        Course courseByName = service.get(COURSE_2.getId(),STUDENT_ID);
        assertMatch(courseByName,COURSE_2);
    }
    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(7,STUDENT_ID);
    }

    @Test
    public void getByName() {
        Course courseByName = service.getByName(COURSE_2.getName(),STUDENT_ID);
        assertMatch(courseByName,COURSE_2);
    }
    @Test(expected = NotFoundException.class)
    public void getByNameNotFound() {
    Course course = service.getByName("HELLO WORLD", STUDENT_ID);
    }

    @Test
    public void getAll() {
        List<Course> courseList = service.getAll(STUDENT_ID);
        assertMatch(courseList, COURSE_2, COURSE_3, COURSE_1);
    }

    @Test
    public void update() throws Exception {
        Course updated = getUpdated();
        service.update(updated,STUDENT_ID);
        assertMatch(service.get(STUDENT_COURSE_ID, STUDENT_ID), updated);
    }

    @Test
    public void getBetween() throws Exception {
        assertMatch(service.getBetweenCost(15500,17000, STUDENT_ID),COURSE_2,COURSE_3 );
    }

    @Test
    public void create() {
        Course newCourse = getCreated();
        Course created = service.create(newCourse, STUDENT_COURSE_ID);
        newCourse.setId(created.getId());
        assertMatch(newCourse, created);
        assertMatch(service.getAll(STUDENT_COURSE_ID), COURSE_2, created, COURSE_3, COURSE_1);
    }
    @Test(expected = DataAccessException.class)
    public void duplicateNumberCreate() {
        service.create(new Course(null, "Новый Курс", 666,1200),STUDENT_ID);
    }
}