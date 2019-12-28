package ru.university;

import ru.university.model.Faculty;
import ru.university.model.UniversityCourse;

import java.util.Arrays;
import java.util.List;

import static ru.university.repository.inmemory.InMemoryUniversityCourseRepository.UNIVERSITY_COURSES;

public class FacultyTestData {
    public static final int FIZFAK_ID = 1;
    public static final int ECONOM_ID = 2;

    public static final Faculty FIZFAK = new Faculty(null, "Физфак", UNIVERSITY_COURSES);
    public static final Faculty ECONOM =new Faculty(null, "Эконом", Arrays.asList(
            new UniversityCourse(null, "Коммунизм", 33, 250f),
            new UniversityCourse(null, "Капитализм", 34, 255f)));
}
