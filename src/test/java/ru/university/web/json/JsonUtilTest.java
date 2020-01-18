package ru.university.web.json;

import org.junit.jupiter.api.Test;
import ru.university.model.Course;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.university.CourseTestData.*;

class JsonUtilTest {

    @Test
    void readWriteValue() throws Exception {
        String json = JsonUtil.writeValue(PROFESSOR_COURSE_1);
        System.out.println(json);
        Course course = JsonUtil.readValue(json, Course.class);
        assertMatch(course, PROFESSOR_COURSE_1);
    }

    @Test
    void readWriteValues() throws Exception {
        String json = JsonUtil.writeValue(UNIVERSITY_COURSES);
        System.out.println(json);
        List<Course> courses = JsonUtil.readValues(json, Course.class);
        assertMatch(courses, UNIVERSITY_COURSES);
    }
}