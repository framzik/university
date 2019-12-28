package ru.university.repository.inmemory;

import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;
import ru.university.model.UniversityCourse;
import ru.university.repository.UniversityCourseRepository;
import ru.university.util.Util;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import static ru.university.FacultyTestData.*;

@Repository
public class InMemoryUniversityCourseRepository implements UniversityCourseRepository {

    private Map<Integer, Map<Integer, UniversityCourse>> facultyCourseMap = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public static final List<UniversityCourse> UNIVERSITY_COURSES = Arrays.asList(
            new UniversityCourse(null, "Теормех", 666, 15250.0f),
            new UniversityCourse(null, "Мат. анализ", 669, 15900.0f),
            new UniversityCourse(null, "Теория струн", 668, 16900.0f)
    );
    public static final List<UniversityCourse> UNIVERSITY_COURSES_ECONOM = Arrays.asList(new UniversityCourse(null, "Коммунизм", 33, 250f),
            new UniversityCourse(null, "Капитализм", 34, 255f));

    {
        UNIVERSITY_COURSES.forEach(universityCourse -> save(universityCourse, FIZFAK_ID));
        UNIVERSITY_COURSES_ECONOM.forEach(universityCourse -> save(universityCourse, ECONOM_ID));
    }

    @Override
    public UniversityCourse save(UniversityCourse universityCourse, int facultyId) {
        Map<Integer, UniversityCourse> universityCourses = facultyCourseMap.computeIfAbsent(facultyId, ConcurrentHashMap::new);
        if (universityCourse.isNew()) {
            universityCourse.setId(counter.incrementAndGet());
            universityCourses.put(universityCourse.getId(), universityCourse);
            return universityCourse;
        }
        return universityCourses.computeIfPresent(universityCourse.getId(), (id, oldUniCources) -> universityCourse);
    }

    @Override
    public boolean delete(int id, int facultyId) {
        Map<Integer, UniversityCourse> universityCourses = facultyCourseMap.get(facultyId);
        return universityCourses != null && universityCourses.remove(id) != null;
    }

    @Override
    public UniversityCourse get(int id, int facultyId) {
        Map<Integer, UniversityCourse> universityCourses = facultyCourseMap.get(facultyId);
        return universityCourses == null ? null : universityCourses.get(id);
    }

    @Override
    public UniversityCourse getByName(String name, int facultyId) {
        Map<Integer, UniversityCourse> universityCourses = facultyCourseMap.get(facultyId);
        return universityCourses.values().stream()
                .filter(course -> name.equals(course.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<UniversityCourse> getAll(int facultyId) {
        return getAllFiltered(facultyId, course -> true);
    }

    private List<UniversityCourse> getAllFiltered(int facultyId, Predicate<UniversityCourse> filter) {
        Map<Integer, UniversityCourse> universityCourses = facultyCourseMap.get(facultyId);

        return CollectionUtils.isEmpty(universityCourses) ? Collections.emptyList() :
                universityCourses.values().stream()
                        .filter(filter)
                        .sorted(Comparator.comparing(UniversityCourse::getName).reversed())
                        .collect(Collectors.toList());
    }

    @Override
    public List<UniversityCourse> getBetweenCost(float startCost, float endCost, int facultyId) {
        return getAllFiltered(facultyId,course -> Util.isBetweenInclusive(course.getCost(),startCost,endCost));
    }
}
