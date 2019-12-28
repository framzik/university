package ru.university.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.university.FacultyTestData;
import ru.university.model.Faculty;
import ru.university.model.UniversityCourse;
import ru.university.repository.FacultyRepository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static ru.university.FacultyTestData.*;
import static ru.university.repository.inmemory.InMemoryUniversityCourseRepository.UNIVERSITY_COURSES;
@Repository
public class InMemoryFacultyRepository implements FacultyRepository {

    private Map<Integer, Faculty> map = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

//    public static final List<Faculty> FACULTIES = Arrays.asList(
//            new Faculty(null, "Физфак", UNIVERSITY_COURSES),
//            new Faculty(null, "Эконом", Arrays.asList(
//                    new UniversityCourse(null, "Коммунизм", 33, 250f),
//                    new UniversityCourse(null, "Капитализм", 34, 255f)))
//    );
//
//    {
//        FACULTIES.forEach(this::save);
//    }

    public void init() {
        map.clear();
        map.put(FacultyTestData.FIZFAK_ID,FIZFAK);
        map.put(FacultyTestData.ECONOM_ID,ECONOM);
    }

    @Override
    public Faculty save(Faculty faculty) {
        if (faculty.isNew()) {
            faculty.setId(counter.incrementAndGet());
            map.put(faculty.getId(), faculty);
            return faculty;
        }
        return map.computeIfPresent(faculty.getId(), (id, oldFaculti) -> faculty);
    }

    @Override
    public boolean delete(int id) {
        return map.remove(id) != null;
    }

    @Override
    public Faculty get(int id) {
        return map.get(id);
    }

    @Override
    public List<Faculty> getAll() {
        return map.values().stream()
                .sorted(Comparator.comparing(Faculty::getName).thenComparing(Faculty::getName))
                .collect(Collectors.toList());
    }

    @Override
    public Faculty getByName(String name) {
        return map.values().stream()
                .filter(r -> name.equals(r.getName()))
                .findFirst()
                .orElse(null);
    }

}
