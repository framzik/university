package ru.university.repository.datajpa;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.university.model.User;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudUserRepository extends JpaRepository<User, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM User u WHERE u.id=?1")
    int delete(int id);

    User getByEmail(String email);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.courses WHERE u.id = ?1 ORDER BY u.name, u.email")
    @EntityGraph(attributePaths = {"courses"}, type = EntityGraph.EntityGraphType.LOAD)
    User getWithCourse(int id);
}
