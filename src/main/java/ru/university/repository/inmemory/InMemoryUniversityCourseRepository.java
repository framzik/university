package ru.university.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.university.model.UniversityCourse;
import ru.university.repository.UniversityCourseRepository;

import java.util.Collection;
import java.util.Collections;

@Repository
public class InMemoryUniversityCourseRepository implements UniversityCourseRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUniversityCourseRepository.class);

    @Override
    public UniversityCourse save(UniversityCourse course) {
        log.info("save {}", course);
        return null;
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return false;
    }

    @Override
    public UniversityCourse get(int id) {
        log.info("get {}", id);
        return null;
    }

    @Override
    public Collection<UniversityCourse> getAll() {
        log.info("getAll");
        return Collections.emptyList();
    }
}
