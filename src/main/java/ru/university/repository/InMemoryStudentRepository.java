package ru.university.repository;

import ru.university.model.Student;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryStudentRepository implements  StudentRepository{
    private Map<Integer, Student> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);
    public static final List<Student> STUDENTS = Arrays.asList(
            new Student(1,"Ямчеков Н.А", "ул. Стокгольма 28", "fr@ya.ru", 111, 4.7f),
            new Student(2, "Белялов Л.Э", "ул. Белогвардейцев 38", "fr@yahoo.com", 112, 4.5f),
            new Student(3,"Новожилов Э.А.", "ул. Васильков 34", "fr@gmail.com", 113, 5.0f),
            new Student(4,"Савчук А.И", "ул. Самойловой 33", "fr@mail.ru", 114, 3.5f)
    );

    {
        STUDENTS.forEach(this::save);
    }

    @Override
    public Student save(Student student) {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public Student get(int id) {
        return null;
    }

    @Override
    public Student getByEmail(String email) {
        return null;
    }

    @Override
    public List<Student> getAll() {
        return null;
    }
}
