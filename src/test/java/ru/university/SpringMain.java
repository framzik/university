package ru.university;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.model.Student;
import ru.university.model.UniversityCourse;
import ru.university.web.rest.StudentRestRestController;
import ru.university.web.rest.UniversityCourseRestController;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import static ru.university.model.Role.ROLE_STUDENT;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            StudentRestRestController studentRestController = appCtx.getBean(StudentRestRestController.class);
            studentRestController.create(new Student(null, "Савчук А.И", "fr@mail.ru", "password",
                            "ул. Самойловой 33", true, 114, 3.5f, EnumSet.of(ROLE_STUDENT)));
            //userRestController.create(new Student(null, "newStudent", "Filatova 16", "fr@kz.ru", 455518, 3.5f));
            System.out.println();

            UniversityCourseRestController universityCourseRestController = appCtx.getBean(UniversityCourseRestController.class);
            List<UniversityCourse> universityCourses = universityCourseRestController.getBetween(15000f, 17000f);
            universityCourses.forEach(System.out::println);
        }
    }
}
