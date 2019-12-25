package ru.university.repository;

import ru.university.model.Faculty;

import java.util.Collection;

public interface FacultyRepository {
    Faculty save(Faculty faculty);

    boolean delete(int id);

    Faculty get(int id);

    Faculty getByName(String name);

    Collection<Faculty> getAll();
}
