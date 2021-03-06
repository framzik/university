package ru.university;

import org.springframework.test.web.servlet.ResultMatcher;
import ru.university.model.Course;
import ru.university.model.User;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.university.TestUtil.readFromJsonMvcResult;
import static ru.university.TestUtil.readListFromJsonMvcResult;

public class CourseTestData {
    public static final int STUDENT_COURSE_ID = 1;
    public static final int PROFESSOR_COURSE_ID = 7;

    public static final Course COURSE_1 = new Course(STUDENT_COURSE_ID, "Теормех", 666, 15250.0f);
    public static final Course COURSE_2 = new Course(STUDENT_COURSE_ID + 1, "Мат. анализ", 669, 15900.0f);
    public static final Course COURSE_3 = new Course(STUDENT_COURSE_ID + 2, "Теория струн", 668, 16900.0f);
    public static final Course COURSE_4 = new Course(STUDENT_COURSE_ID + 3,"Начерталка", 670, 15251.0f);
    public static final Course COURSE_5 = new Course(STUDENT_COURSE_ID + 4,"Англ. язык", 671, 25900.0f);
    public static final Course COURSE_6 = new Course(STUDENT_COURSE_ID + 5,"Основы программирования", 672, 16999.0f);
    public static final Course PROFESSOR_COURSE_1 = new Course(PROFESSOR_COURSE_ID, "Капитализм", 34, 255f);
    public static final Course PROFESSOR_COURSE_2 = new Course(PROFESSOR_COURSE_ID + 1, "Марксизм", 35, 275f);

    public static final List<Course> UNIVERSITY_COURSES = List.of(COURSE_2, COURSE_3, COURSE_1);

    public static Course getNew() {
        return new Course(null, "Созданный курс", 6666, 15000f);
    }

    public static Course getUpdated() {
        return new Course(STUDENT_COURSE_ID, "Обновленный курс", COURSE_1.getNumber(), COURSE_1.getCost());
    }

    public static void assertMatch(Course actual, Course expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "user");
    }

    public static void assertMatch(Iterable<Course> actual, Course... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Course> actual, Iterable<Course> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }

    public static ResultMatcher contentJson(Course... expected) {
        return result -> assertMatch(readListFromJsonMvcResult(result, Course.class), List.of(expected));
    }

    public static ResultMatcher contentJson(Course expected) {
        return result -> assertMatch(readFromJsonMvcResult(result, Course.class), expected);
    }
}
