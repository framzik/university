package ru.university;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.university.model.Student;
import ru.university.repository.StudentRepository;
import ru.university.service.StudentService;

import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

        StudentRepository studentRepository = appCtx.getBean(StudentRepository.class);
        studentRepository.getAll();

        StudentService studentService = appCtx.getBean(StudentService.class);
        studentService.create(new Student(null, "newStudent","Filatova 16", "fr@kz.ru", 455518, 3.5f ));
        appCtx.close();
    }
}
