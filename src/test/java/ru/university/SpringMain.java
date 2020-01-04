package ru.university;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.model.Course;
import ru.university.web.rest.CourseRestController;

import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            //userRestController.create(new Student(null, "newStudent", "Filatova 16", "fr@kz.ru", 455518, 3.5f));
            System.out.println();

            CourseRestController courseRestController = appCtx.getBean(CourseRestController.class);
            List<Course> cours = courseRestController.getBetween(15000f, 17000f);
            cours.forEach(System.out::println);
        }
    }
}
