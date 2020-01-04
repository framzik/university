package ru.university.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.university.UserTestData;
import ru.university.model.Student;
import ru.university.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.university.UserTestData.YAMCHEKOV;
@Repository
public class InMemoryUserRepository {
    private static AtomicInteger counter = new AtomicInteger(0);

    Map<Integer, User> map = new ConcurrentHashMap<>();

    public void init() {
        map.clear();
        map.put(UserTestData.STUDENT_ID, YAMCHEKOV);
    }

    public User save(User entry) {
        if (entry.isNew()) {
            entry.setId(counter.incrementAndGet());
            map.put(entry.getId(), entry);
            return entry;
        }
        return map.computeIfPresent(entry.getId(), (id, oldT) -> entry);
    }

    public boolean delete(int id) {
        return map.remove(id) != null;
    }



    Collection<User> getCollection() {
        return map.values();
    }
}
