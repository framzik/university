package ru.university.service;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.university.CourseTestData;
import ru.university.model.User;
import ru.university.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.university.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {
    @Autowired
    private UserService service;

    @Test
    public void create() throws Exception {
        User newUser = getNew();
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(newUser, created);
        assertMatch(service.getAll(), created, BELYALOV, GRIGOREV, NOVOGILOV, SAVCHYK, STAROSTENKO, YAMCHEKOV);
    }

    @Test
    public void delete() {
        service.delete(STUDENT_ID + 3);
        assertMatch(service.getAll(), GRIGOREV, NOVOGILOV, SAVCHYK, STAROSTENKO, YAMCHEKOV);
    }

    @Test()
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(7));
    }

    @Test
    public void get() {
        User user = service.get(STUDENT_ID + 5);
        assertMatch(user, SAVCHYK);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(7));
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail(SAVCHYK.getEmail());
        assertMatch(user, SAVCHYK);
    }

    @Test
    public void getByEmailNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.getByEmail("no@email.ru"));
    }


    @Test
    public void getAll() {
        List<User> all = (List<User>) service.getAll();
        assertMatch(all, BELYALOV, GRIGOREV, NOVOGILOV, SAVCHYK, STAROSTENKO, YAMCHEKOV);
    }

    @Test
    public void update() {
        User updated = getUpdated();
        service.update(updated);
        assertMatch(service.get(STUDENT_ID), updated);
    }

    @Test
    public void getWithMeals() throws Exception {
        User user = service.getWithCourse(STUDENT_ID);
        assertMatch(user, YAMCHEKOV);
        CourseTestData.assertMatch(user.getCourses(), CourseTestData.UNIVERSITY_COURSES);
    }

    @Test
    public void getWithMealsNotFound() throws Exception {
        assertThrows(NotFoundException.class, () ->
                service.getWithCourse(12));
    }
}