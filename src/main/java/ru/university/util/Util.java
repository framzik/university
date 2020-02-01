package ru.university.util;

import org.springframework.lang.Nullable;
import ru.university.model.Role;
import ru.university.model.User;
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
}
