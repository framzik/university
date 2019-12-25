package ru.university;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.model.Student;
import ru.university.web.student.StudentRestController;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            StudentRestController studentRestController = appCtx.getBean(StudentRestController.class);
            studentRestController.create(new Student(null, "newStudent", "Filatova 16", "fr@kz.ru", 455518, 3.5f));
        }
    }
}
