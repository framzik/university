package ru.university;

import ru.university.model.Course;

import java.util.Arrays;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;

public class CourseTestData {
    public static final int STUDENT_COURSE_ID = 1;
    public static final int PROFESSOR_COURSE_ID = 4;

    public static final Course COURSE_1 = new Course(STUDENT_COURSE_ID, "Теормех", 666, 15250.0f);
    public static final Course COURSE_2 = new Course(STUDENT_COURSE_ID + 1, "Мат. анализ", 669, 15900.0f);
    public static final Course COURSE_3 = new Course(STUDENT_COURSE_ID + 2, "Теория струн", 668, 16900.0f);
    public static final Course PROFESSOR_COURSE_1 = new Course(PROFESSOR_COURSE_ID, "Капитализм", 34, 255f);
    public static final Course PROFESSOR_COURSE_2 = new Course(PROFESSOR_COURSE_ID + 1, "Марксизм", 35, 275f);

    public static final List<Course> UNIVERSITY_COURSES = List.of(COURSE_1, COURSE_2, COURSE_3);

    public static Course getCreated() {
        return new Course(null, "Созданный курс", 6666, 15000f);
    }

    public static Course getUpdated() {
        return new Course(STUDENT_COURSE_ID,"Обновленный курс", COURSE_1.getNumber(), COURSE_1.getCost());
    }

    public static void assertMatch(Course actual, Course expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected,"user");
    }

    public static void assertMatch(Iterable<Course> actual, Course... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Course> actual, Iterable<Course> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("user").isEqualTo(expected);
    }
}
