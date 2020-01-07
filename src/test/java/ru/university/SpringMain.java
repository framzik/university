package ru.university;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.model.Course;
import ru.university.model.Role;
import ru.university.model.User;
import ru.university.web.rest.CourseRestController;
import ru.university.web.rest.UserRestController;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml","spring/inmemory.xml" )) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserRestController controller = appCtx.getBean(UserRestController.class);
            controller.create(new User(null, "CreatedByMain NewUser","main@ya.ru", "password","ул Лопухова 22", Role.ROLE_STUDENT));
            System.out.println();

            CourseRestController courseRestController = appCtx.getBean(CourseRestController.class);
            List<Course> courses = courseRestController.getBetween(15000f, 16800f);
            courses.forEach(System.out::println);
        }
    }
}
