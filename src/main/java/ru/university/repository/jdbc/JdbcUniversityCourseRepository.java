package ru.university.repository.jdbc;

import org.springframework.stereotype.Repository;
import ru.university.model.UniversityCourse;
import ru.university.repository.UniversityCourseRepository;

import java.util.List;
@Repository
public class JdbcUniversityCourseRepository implements UniversityCourseRepository {
    @Override
    public UniversityCourse save(UniversityCourse course, int facultyId) {
        return null;
    }

    @Override
    public boolean delete(int id, int facultyId) {
        return false;
    }

    @Override
    public UniversityCourse get(int id, int facultyId) {
        return null;
    }

    @Override
    public UniversityCourse getByName(String name, int facultyId) {
        return null;
    }

    @Override
    public List<UniversityCourse> getAll(int facultyId) {
        return null;
    }

    @Override
    public List<UniversityCourse> getBetweenCost(float startCost, float endCost, int facultyId) {
        return null;
    }
}
