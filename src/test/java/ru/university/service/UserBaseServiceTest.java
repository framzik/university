package ru.university.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.university.model.Role;
import ru.university.model.User;
import ru.university.util.exception.NotFoundException;

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
public class UserBaseServiceTest {
    @Autowired
    private UserBaseService<User> service;


    @Test
    public void create() throws Exception {
        User newUser = new User(null, "Новый Юзер", "ny@ya.ru", "password", "Address", true, new Date(), Collections.singleton(Role.ROLE_STUDENT));
        User created = service.create(newUser);
        newUser.setId(created.getId());
        assertMatch(newUser, created);
        assertMatch(service.getAll(), BELYALOV, GRIGOREV, NOVOGILOV, created, SAVCHYK, STAROSTENKO, YAMCHEKOV);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateMailCreate() {
        service.create(new User(null, "Новый Юзер", BELYALOV.getEmail(), "password", "Address", true, new Date(), Collections.singleton(Role.ROLE_STUDENT)));
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
        User updated = new User(YAMCHEKOV);
        updated.setAddress("ул. Михельштейна 98");
        updated.setName("vasya");
        service.update(updated);
        assertMatch(service.get(STUDENT_ID), updated);
    }
}