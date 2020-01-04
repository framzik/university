package ru.university.web.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.university.model.User;
import ru.university.repository.inmemory.InMemoryUserRepository;
import ru.university.util.exception.NotFoundException;

import static ru.university.UserTestData.YAMCHEKOV;


@ContextConfiguration({"classpath:spring/spring-app.xml"
                    ,"classpath:spring/inmemory.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserRestControllerTest<T extends User> {
    @Autowired
    private UserRestController controller;
    @Autowired
    private InMemoryUserRepository repository;

    @Before
    public void setUp() {
        // re-initialize
        repository.init();
    }

    @Test
    public void getByEmail() {
        User user = controller.getByMail("fr@ya.ru");
        user.setId(1);
        Assert.assertEquals(user, YAMCHEKOV);
    }

    @Test(expected = NotFoundException.class)
    public void getByEmailNotFound() {
        User Savchyk = controller.getByMail("fr2@ya.ru");
    }


}