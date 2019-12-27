package ru.university.repository;

import org.springframework.lang.Nullable;
import ru.university.model.UniversityCourse;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface UniversityCourseRepository {
    UniversityCourse save(UniversityCourse course, int facultyId);

    boolean delete(int id, int facultyId);

    UniversityCourse get(int id, int facultyId);

    UniversityCourse getByName(String name, int facultyId);

    List<UniversityCourse> getAll(int facultyId);

    List<UniversityCourse> getBetweenCost(@Nullable float startCost, @Nullable float endCost, int facultyId);
}
