package ru.university.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.university.model.Professor;

@Transactional(readOnly = true)
public interface CrudProfessorRepository extends JpaRepository<Professor, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE from Professor p WHERE p.id=:id")
    int delete(@Param("id") int id);

    Professor getByName(String name);
}
