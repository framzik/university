package ru.university.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.university.model.Role;
import ru.university.model.User;
import ru.university.util.exception.NotFoundException;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static ru.university.UserTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDb.sql", config = @SqlConfig(encoding = "UTF-8"))
public class UserServiceTest {
    @Autowired
    private UserService service;

    @Autowired
    private CacheManager cacheManager;

    @Before
    public void setUp(){
        cacheManager.getCache("users").clear();
    }

    @Test
    public void create() throws Exception {
        User newUser = getNew();
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(newUser, created);
        assertMatch(service.getAll(),created, BELYALOV, GRIGOREV, NOVOGILOV,  SAVCHYK, STAROSTENKO, YAMCHEKOV);
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
}