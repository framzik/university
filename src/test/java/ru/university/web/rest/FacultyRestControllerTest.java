package ru.university.web.rest;

import org.junit.*;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.FacultyTestData;
import ru.university.model.Faculty;
import ru.university.repository.inmemory.InMemoryFacultyRepository;
import ru.university.util.exception.NotFoundException;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static ru.university.FacultyTestData.ECONOM;

public class FacultyRestControllerTest {
    private static ConfigurableApplicationContext appCtx;
    private static FacultyRestController controller;

    @BeforeClass
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("\n" + Arrays.toString(appCtx.getBeanDefinitionNames()) + "\n");
        controller = appCtx.getBean(FacultyRestController.class);
    }

    @Before
    public void setUp() throws Exception {
        // re-initialize
        InMemoryFacultyRepository repository = appCtx.getBean(InMemoryFacultyRepository.class);
        repository.init();
    }

    @AfterClass
    public static void afterClass() {
        appCtx.close();
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