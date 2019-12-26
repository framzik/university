package ru.university.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.university.model.Faculty;
import ru.university.model.UniversityCourse;
import ru.university.repository.FacultyRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.university.repository.inmemory.InMemoryUniversityCourseRepository.UNIVERSITY_COURSES;
@Repository
public class InMemoryFacultyRepository implements FacultyRepository {

    public static final int FIZFAK_ID = 1;
    public static final int ECONOM_ID = 2;

    private Map<Integer, Faculty> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public static final List<Faculty> FACULTIES = Arrays.asList(
            new Faculty(null, "Физфак", UNIVERSITY_COURSES),
            new Faculty(null, "Эконом", Arrays.asList(
                    new UniversityCourse(null, "Коммунизм", 33, 250f),
                    new UniversityCourse(null, "Капитализм", 34, 255f)))
    );

    {
        FACULTIES.forEach(this::save);
    }

    @Override
    public Faculty save(Faculty faculty) {
        if (faculty.isNew()) {
            faculty.setId(counter.incrementAndGet());
            repository.put(faculty.getId(), faculty);
            return faculty;
        }
        return repository.computeIfPresent(faculty.getId(), (id, oldFaculti) -> faculty);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id) != null;
    }

    @Override
    public Faculty get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Faculty> getAll() {
        return repository.values().stream()
                .sorted(Comparator.comparing(Faculty::getName).thenComparing(Faculty::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Faculty getByName(String name) {
        return repository.values().stream()
                .filter(r -> name.equals(r.getName()))
                .findFirst()
                .orElse(null);
    }

}
