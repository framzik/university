package ru.university.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.university.model.Course;

import static ru.university.CourseTestData.*;

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
    }

    @Test
    public void get() {
    }

    @Test
    public void getByName() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getBetweenCost() {
    }

    @Test
    public void create() {
        Course newCourse = getCreated();
        Course created = service.create(newCourse, STUDENT_COURSE_ID);
        newCourse.setId(created.getId());
        assertMatch(newCourse, created);
        assertMatch(service.getAll(STUDENT_COURSE_ID), COURSE_2, created, COURSE_3, COURSE_1);
    }
}