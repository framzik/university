package ru.university.util;

import org.springframework.lang.Nullable;
import ru.university.model.Course;
import ru.university.model.Role;
import ru.university.model.User;
import ru.university.to.CourseTo;
import ru.university.to.UserTo;

public class Util {

    private Util() {
    }

    public static <T extends Comparable<? super T>> boolean isBetweenInclusive(T value, @Nullable T start, @Nullable T end) {
        return (start == null || value.compareTo(start) >= 0) && (end == null || value.compareTo(end) <= 0);
    }

    public static User createNewUserFromTo(UserTo userTo) {
        return new User(null, userTo.getName(), userTo.getEmail().toLowerCase(), userTo.getPassword(), userTo.getAddress(), Role.ROLE_STUDENT);
    }
    public static User updateFromTo(User user, UserTo userTo) {
        user.setName(userTo.getName());
        user.setEmail(userTo.getEmail().toLowerCase());
        user.setPassword(userTo.getPassword());
        user.setAddress(userTo.getAddress());
        return user;
    }

    public static Course createNewCourseFromTo(CourseTo courseTo) {
        return new Course(null, courseTo.getName(), courseTo.getNumber(),courseTo.getCost());
    }
    public static Course updateFromTo(Course course, CourseTo courseTo) {
        course.setName(courseTo.getName());
        course.setNumber(courseTo.getNumber());
        course.setCost(courseTo.getCost());
        return course;
    }
}
