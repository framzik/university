package ru.university;

import ru.university.model.*;

import java.util.Collections;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.university.model.Role.ROLE_PROFESSOR;
import static ru.university.model.Role.ROLE_STUDENT;


public class UserTestData {
    public static final int STUDENT_ID = 1;
    public static final int PROFESSOR_ID = 2;

    public static final User YAMCHEKOV = new User(1, "Ямчеков Н.А", "fr@ya.ru", "password", "ул. Стокгольма 28", true, new Date(), EnumSet.of(ROLE_STUDENT));
    public static final User GRIGOREV = new User(2, "Григорьев Е.В.", "gr@ya.ru", "password", "ул. Глушко 12В", true, new Date(), EnumSet.of(ROLE_PROFESSOR));
    public static final User STAROSTENKO = new User(3, "Старостенко В.В", "st@ya.ru", "password", "ул. Мамонтова 43", true, new Date(), EnumSet.of(ROLE_PROFESSOR));
    public static final User BELYALOV = new User(4, "Белялов Л.Э", "fr@yahoo.com", "password", "ул. Белогвардейцев 38", true, new Date(), EnumSet.of(ROLE_STUDENT));
    public static final User NOVOGILOV = new User(5, "Новожилов Э.А.", "fr@gmail.com", "password", "ул. Васильков 34", true, new Date(), EnumSet.of(ROLE_STUDENT));
    public static final User SAVCHYK = new User(6, "Савчук А.И", "fr@mail.ru", "password", "ул. Самойловой 33", true, new Date(), EnumSet.of(ROLE_STUDENT));

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass","New", false, new Date(), Collections.singleton(ROLE_STUDENT));
    }

    public static User getUpdated() {
        User updated = new User(YAMCHEKOV);
        updated.setAddress("Upp");
        updated.setName("Gala");
        return updated;
    }

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "registered", "roles");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("registered", "roles").isEqualTo(expected);
    }
}
