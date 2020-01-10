package ru.university.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.university.CourseTestData;
import ru.university.model.User;
import ru.university.util.exception.NotFoundException;

import java.util.List;

import static ru.university.UserTestData.*;

public class UserServiceTest extends AbstractServiceTest {
    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp() {
        cacheManager.getCache("users").clear();
    }

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
        service.delete(BELYALOV.getId());
        assertMatch(service.getAll(), GRIGOREV, NOVOGILOV, SAVCHYK, STAROSTENKO, YAMCHEKOV);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() {
        service.delete(7);
    }


    @Test
    public void get() {
        User user = service.get(SAVCHYK.getId());
        assertMatch(user, SAVCHYK);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        service.get(7);
    }

    @Test
    public void getByEmail() {
        User user = service.getByEmail(SAVCHYK.getEmail());
        assertMatch(user, SAVCHYK);
    }

    @Test(expected = NotFoundException.class)
    public void getByEmailNotFound() {
        User user = service.getByEmail("no@email.ru");
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
        assertMatch(user,YAMCHEKOV);
        CourseTestData.assertMatch(user.getCourses(), CourseTestData.UNIVERSITY_COURSES);
    }

    @Test(expected = NotFoundException.class)
    public void getWithMealsNotFound() throws Exception {
        service.getWithCourse(12);
    }
}