package ru.university.web.rest;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ru.university.FacultyTestData;
import ru.university.model.Faculty;
import ru.university.repository.inmemory.InMemoryFacultyRepository;
import ru.university.util.exception.NotFoundException;

import java.util.Collection;

import static ru.university.FacultyTestData.ECONOM;
@ContextConfiguration("classpath:spring/spring-app.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class FacultyRestControllerSpringTest {
    @Autowired
    private FacultyRestController controller;

    @Autowired
    private InMemoryFacultyRepository repository;

    @Before
    public void setUp() throws Exception {
        // re-initialize
        repository.init();
    }

    @org.junit.Test
    public void getAll() {
    }

    @org.junit.Test
    public void get() {
    }

    @org.junit.Test
    public void create() {
    }

    @org.junit.Test
    public void delete() {
        controller.delete(FacultyTestData.FIZFAK_ID);
        Collection<Faculty> faculties = controller.getAll();
        Assert.assertEquals(faculties.size(),1);
        Assert.assertEquals(faculties.iterator().next(),ECONOM);
    }


    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        controller.delete(10);
    }

    @org.junit.Test
    public void update() {
    }
}
