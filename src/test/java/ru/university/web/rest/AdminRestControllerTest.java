package ru.university.web.rest;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.repository.inmemory.InMemoryUserRepository;
import ru.university.util.exception.NotFoundException;
import ru.university.web.user.AdminRestController;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.university.UserTestData.STUDENT_ID;


public class AdminRestControllerTest {
    private static final Logger log = LoggerFactory.getLogger(AdminRestControllerTest.class);

    private static ConfigurableApplicationContext appCtx;
    private static AdminRestController controller;

    @BeforeAll
    public static void beforeClass() {
        appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml");
        log.info("\n{}\n", Arrays.toString(appCtx.getBeanDefinitionNames()));
        controller = appCtx.getBean(AdminRestController.class);
    }

    @AfterAll
    public static void afterClass() {
        appCtx.close();
    }

    @BeforeEach
    public void setUp()  {
        // re-initialize
        InMemoryUserRepository repository = appCtx.getBean(InMemoryUserRepository.class);
        repository.init();
    }

    @Test
    public void delete()  {
        controller.delete(STUDENT_ID);
        assertThrows(NotFoundException.class, () ->
                controller.get(STUDENT_ID));
    }

    @Test
    public void deleteNotFound()  {
        assertThrows(NotFoundException.class, () ->
                controller.delete(10));
    }
}