package ru.university;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.model.Student;
import ru.university.model.UniversityCourse;
import ru.university.web.rest.UserRestController;
import ru.university.web.rest.UniversityCourseRestController;

import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            UserRestController userRestController = appCtx.getBean(UserRestController.class);
            //userRestController.create(new Student(null, "newStudent", "Filatova 16", "fr@kz.ru", 455518, 3.5f));
            System.out.println();

            UniversityCourseRestController universityCourseRestController = appCtx.getBean(UniversityCourseRestController.class);
            List<UniversityCourse> universityCourses = universityCourseRestController.getBetween(15500f, 16000f);
            universityCourses.forEach(System.out::println);
        }
    }
}
