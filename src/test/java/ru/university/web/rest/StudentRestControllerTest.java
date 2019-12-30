package ru.university.web.rest;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.university.model.Student;
import ru.university.repository.inmemory.InMemoryStudentRepository;
import ru.university.util.exception.NotFoundException;

import static ru.university.UserTestData.STUDENT;


@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class StudentRestControllerTest {
    @Autowired
    private StudentRestController controller;
    @Autowired
    private InMemoryStudentRepository repository;

    @Before
    public void setUp() throws Exception {
        // re-initialize
        repository.init();
    }

    @Test
    public void getByEmail() {
        Student Savchyk = controller.getByMail("fr@ya.ru");
        Assert.assertEquals(Savchyk, STUDENT);
    }

    @Test(expected = NotFoundException.class)
    public void getByEmailNotFound() {
        Student Savchyk = controller.getByMail("fr2@ya.ru");
        Assert.assertEquals(Savchyk, STUDENT);
    }


}