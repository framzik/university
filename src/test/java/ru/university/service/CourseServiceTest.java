package ru.university.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.university.model.Course;
import ru.university.util.exception.NotFoundException;

import javax.persistence.PersistenceException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;
import static ru.university.CourseTestData.*;
import static ru.university.UserTestData.PROFESSOR_ID;
import static ru.university.UserTestData.STUDENT_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql", config = @SqlConfig(encoding = "UTF-8"))
public class CourseServiceTest {

    private static final Logger log = getLogger("result");

    private static StringBuilder results = new StringBuilder();

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
    @Rule
    public Stopwatch stopwatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms/n");
        }
    };

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }

    @Autowired
    CourseService service;


    @Test
    public void delete() {
        service.delete(COURSE_2.getId(), STUDENT_ID);
        thrown.expect(NotFoundException.class);
        service.get(COURSE_2.getId(),STUDENT_ID);
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(7, STUDENT_ID);
    }

    @Test
    public void get() {
        Course courseByName = service.get(COURSE_2.getId(), STUDENT_ID);
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
        service.get(COURSE_1.getId(), PROFESSOR_ID);
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
    public void update()  {
        Course updated = getUpdated();
        service.update(updated, STUDENT_ID);
        assertMatch(service.get(STUDENT_COURSE_ID, STUDENT_ID), updated);
    }

    @Test
    public void updateNotFound()  {
        thrown.expect(NotFoundException.class);
        thrown.expectMessage("Not found entity with id=" + COURSE_1.getId());
        service.update(COURSE_1,PROFESSOR_ID);
    }

    @Test
    public void getBetween()  {
        assertMatch(service.getBetweenCost(15500, 17000, STUDENT_ID), COURSE_2, COURSE_3);
    }

    @Test
    public void create() {
        Course newCourse = getCreated();
        Course created = service.create(newCourse, STUDENT_COURSE_ID);
        newCourse.setId(created.getId());
        assertMatch(newCourse, created);
        //assertMatch(service.getAll(STUDENT_COURSE_ID), COURSE_2, created, COURSE_3, COURSE_1);
    }

    @Test(expected = PersistenceException.class)
    public void duplicateNumberCreate() {
        service.create(new Course(null, "Новый Курс", 666, 1200), STUDENT_ID);
    }
}