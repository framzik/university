package ru.university.web.json;

import org.junit.jupiter.api.Test;
import ru.university.UserTestData;
import ru.university.model.Course;
import ru.university.model.User;

import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
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

    @Test
    void testWriteOnlyAccess() throws Exception {
        String json = JsonUtil.writeValue(UserTestData.YAMCHEKOV);
        System.out.println(json);
        assertThat(json, not(containsString("password")));
        String jsonWithPass = JsonUtil.writeAdditionProps(UserTestData.YAMCHEKOV, "password", "newPass");
        System.out.println(jsonWithPass);
        User user = JsonUtil.readValue(jsonWithPass, User.class);
        assertEquals(user.getPassword(), "newPass");
    }
}