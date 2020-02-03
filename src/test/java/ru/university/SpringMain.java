package ru.university;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.model.Course;
import ru.university.model.Role;
import ru.university.model.User;
import ru.university.web.course.CourseRestController;
import ru.university.web.user.AdminRestController;

import java.util.Arrays;
import java.util.List;

import static ru.university.TestUtil.mockAuthorize;
import static ru.university.UserTestData.YAMCHEKOV;

public class SpringMain {
    public static void main(String[] args) {

        try (ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml", "spring/inmemory.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.getAll();
            System.out.println();
            mockAuthorize(YAMCHEKOV);
            CourseRestController courseRestController = appCtx.getBean(CourseRestController.class);
            List<Course> courses = courseRestController.getBetween(15000f, 16800f);
            courses.forEach(System.out::println);
        }
    }
}
