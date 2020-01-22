package ru.university.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.university.model.User;
import ru.university.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Repository
public class DataJpaUserRepository implements UserRepository {

    public static final Sort SORT_NAME_EMAIL = Sort.by(Sort.Direction.ASC,"name", "email");

    @Autowired
    CrudUserRepository crudUserRepository;

    @Override
    public User save(User entity) {
        return crudUserRepository.save(entity);
    }

    @Override
    public boolean delete(int id) {
        return crudUserRepository.delete(id)!=0;
    }

    @Override
    public User get(int id) {
        return crudUserRepository.findById(id).orElse(null);
    }

    @Override
    public User getByEmail(String email) {
        return crudUserRepository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }

    @Override
    public User getWithCourse(int id) {
        return crudUserRepository.getWithCourse(id);
    }
}
