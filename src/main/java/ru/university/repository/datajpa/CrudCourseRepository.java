package ru.university.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.university.model.Course;

import java.util.List;
import java.util.Optional;

@Transactional(readOnly = true)
public interface CrudCourseRepository extends JpaRepository<Course, Integer> {
    @Transactional
    @Modifying
    @Query("DELETE FROM Course c WHERE c.id=:id AND c.user.id=:userId")
    int delete(@Param("id") int id, @Param("userId") int userId);

    @Override
    @Transactional
    Course save(Course s);

    @Query("select c from Course c WHERE c.user.id =:userId AND c.name=:name")
    Course getByName(@Param("name") String name,@Param("userId") int userId);

    @Query("SELECT c FROM Course c WHERE c.user.id=?1 ORDER BY c.name")
    List<Course> getAll(int userId);

    @Query("SELECT c FROM Course c WHERE c.user.id = :userId AND c.cost>= :startCost AND c.cost<= :endCost order by c.name")
    List<Course> getBetweenCost(@Param("startCost") float startCost, @Param("endCost") float endCost, @Param("userId") int userId);

    @Query("SELECT c FROM Course c JOIN FETCH c.user where c.id=?1 AND c.user.id=?2")
    Course getWithUser(int id, int userId);
}
