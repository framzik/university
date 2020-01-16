package ru.university.web.rest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import ru.university.repository.inmemory.InMemoryUserRepository;
import ru.university.util.exception.NotFoundException;
import ru.university.web.user.AdminRestController;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.university.UserTestData.STUDENT_ID;


@SpringJUnitConfig(locations = {"classpath:spring/spring-app.xml", "classpath:spring/inmemory.xml"})
public class InMemoryAdminRestControllerSpringTest {

    @Autowired
    private AdminRestController controller;

    @Autowired
    private InMemoryUserRepository repository;

    @BeforeEach
    public void setUp()  {
        repository.init();
    }

    @Test
    public void delete()  {
        controller.delete(STUDENT_ID);
        assertThrows(NotFoundException.class,()->
        controller.get(STUDENT_ID));
    }

    @Test
    public void deleteNotFound()  {
        assertThrows(NotFoundException.class,()->
        controller.delete(10));
    }
}
