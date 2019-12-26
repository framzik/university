package ru.university.repository;

import ru.university.model.UniversityCourse;

import java.util.Collection;
import java.util.List;

public interface UniversityCourseRepository {
    UniversityCourse save(UniversityCourse course, int facultyId);

    boolean delete(int id, int facultyId);

    UniversityCourse get(int id, int facultyId);

    UniversityCourse getByName(String name, int facultyId);

    List<UniversityCourse> getAll(int facultyId);
}
