package ru.university.repository;

import ru.university.model.UniversityCourse;

import java.util.Collection;

public interface UniversityCourseRepository {
    UniversityCourse save(UniversityCourse course);

    boolean delete(int id);

    UniversityCourse get(int id);

    UniversityCourse getByName(String name);

    Collection<UniversityCourse> getAll();
}
