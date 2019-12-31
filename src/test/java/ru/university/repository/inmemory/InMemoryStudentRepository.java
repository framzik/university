package ru.university.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.university.UserTestData;
import ru.university.model.Student;
import ru.university.repository.StudentRepository;

import java.util.*;
import java.util.stream.Collectors;

import static ru.university.UserTestData.STUDENT;
import static ru.university.model.Role.ROLE_STUDENT;

@Repository
public class InMemoryStudentRepository extends InMemoryUserRepository<Student> implements StudentRepository {
    public static final Student YAMCHEKOV = new Student(null, "Ямчеков Н.А", "fr@ya.ru", "password", "ул. Стокгольма 28", true, 111, 4.33f, EnumSet.of(ROLE_STUDENT));
    public static final List<Student> STUDENTS = Arrays.asList(
            YAMCHEKOV,
            new Student(null, "Белялов Л.Э", "fr@yahoo.com", "password", "ул. Белогвардейцев 38", true, 112, 4.5f, EnumSet.of(ROLE_STUDENT)),
            new Student(null, "Новожилов Э.А.", "fr@gmail.com", "password", "ул. Васильков 34", true, 113, 5.0f, EnumSet.of(ROLE_STUDENT)),
            new Student(null, "Савчук А.И", "fr@mail.ru", "password", "ул. Самойловой 33", true, 114, 3.5f, EnumSet.of(ROLE_STUDENT))
    );

//    {
//        STUDENTS.forEach(this::save);
//    }


    public void init() {
        map.clear();
    map.put(UserTestData.STUDENT_ID, YAMCHEKOV);
    }



    public Student getByEmail(String email) {
        return getCollection().stream()
                .filter(u -> email.equals(u.getEmail()))
                .findFirst()
                .orElse(null);
    }


    public Collection<Student> getAll() {
        return getCollection().stream()
                .sorted(Comparator.comparing(Student::getName).thenComparing(Student::getRecordNumber))
                .collect(Collectors.toList());
    }
}