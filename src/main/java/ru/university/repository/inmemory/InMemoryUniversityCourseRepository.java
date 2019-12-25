package ru.university.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.university.model.Student;
import ru.university.model.UniversityCourse;
import ru.university.repository.UniversityCourseRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUniversityCourseRepository implements UniversityCourseRepository {
    private Map<Integer, UniversityCourse> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public static final List<UniversityCourse> UNIVERSITY_COURSES = Arrays.asList(
            new UniversityCourse(null,"Теормех", 666,15250.0f),
            new UniversityCourse(null,"Мат. анализ", 669,15900.0f)
    );

    {
        UNIVERSITY_COURSES.forEach(this::save);
    }

    @Override
    public UniversityCourse save(UniversityCourse universityCourse) {
        if(universityCourse.isNew()){
            universityCourse.setId(counter.incrementAndGet());
            repository.put(universityCourse.getId(),universityCourse);
            return universityCourse;
        }
        return repository.computeIfPresent(universityCourse.getId(), (id,oldUniCource)->universityCourse);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id)!=null;
    }

    @Override
    public UniversityCourse get(int id) {
        return repository.get(id);
    }

    @Override
    public UniversityCourse getByName(String name) {
        return repository.values().stream()
                .filter(r -> name.equals(r.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<UniversityCourse> getAll() {
        return repository.values();
    }
}
