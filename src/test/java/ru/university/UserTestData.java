package ru.university;

import ru.university.model.Faculty;
import ru.university.model.Professor;
import ru.university.model.Student;
import ru.university.model.UniversityCourse;

import java.util.Arrays;
import java.util.EnumSet;

import static ru.university.model.Role.ROLE_PROFESSOR;
import static ru.university.model.Role.ROLE_STUDENT;
import static ru.university.repository.inmemory.InMemoryUniversityCourseRepository.UNIVERSITY_COURSES;

public class UserTestData {
    public static final int STUDENT_ID = 1;
    public static final int PROFESSOR_ID = 2;

    public static final Student STUDENT = new Student(null, "Ямчеков Н.А", "fr@ya.ru", "password", "ул. Стокгольма 28", true, 111, 4.33f, EnumSet.of(ROLE_STUDENT));
    public static final Professor PROFESSOR = new Professor(null,"Григорьев Е.В.","gr@ya.ru","password","ул. Глушко 12В", true,EnumSet.of(ROLE_PROFESSOR),"+79787555555",45322f);
}
