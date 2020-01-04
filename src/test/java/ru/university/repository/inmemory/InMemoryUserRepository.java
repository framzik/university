package ru.university.repository.inmemory;

import ru.university.UserTestData;
import ru.university.model.User;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.university.UserTestData.YAMCHEKOV;

public class InMemoryUserRepository<T extends User> {
    private static AtomicInteger counter = new AtomicInteger(0);

    Map<Integer, T> map = new ConcurrentHashMap<>();

    public void init() {
        map.clear();
        map.put(UserTestData.STUDENT_ID, (T)YAMCHEKOV);
    }

    public T save(T entry) {
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

    public T get(int id) {
        return map.get(id);
    }

    Collection<T> getCollection() {
        return map.values();
    }
}
