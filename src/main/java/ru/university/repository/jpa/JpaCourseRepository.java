package ru.university.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.university.model.Course;
import ru.university.model.User;
import ru.university.repository.CourseRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JpaCourseRepository implements CourseRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    @Transactional
    public Course save(Course course, int userId) {
        if (!course.isNew() && get(course.getId(), userId) == null) {
            return null;
        }
        course.setUser(em.getReference(User.class, userId));
        if (course.isNew()) {
            em.persist(course);
            return course;
        } else {
            em.merge(course);
            return course;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Course.DELETE)
                .setParameter("id", id)
                .setParameter("userId", userId)
                .executeUpdate() != 0;
    }

    @Override
    public Course get(int id, int userId) {
        var course = em.find(Course.class, id);
        return course != null && course.getUser().getId() == userId ? course : null;
    }

    @Override
    public Course getByName(String name, int userId) {
        var courses = em.createNamedQuery(Course.BYNAME, Course.class).
                setParameter(1, name)
                .setParameter(2, userId)
                .getResultList();
        return DataAccessUtils.singleResult(courses);
    }

    @Override
    public List<Course> getAll(int userId) {
        return em.createNamedQuery(Course.ALL_SORTED, Course.class)
                .setParameter("userId", userId)
                .getResultList();
    }

    @Override
    public List<Course> getBetweenCost(float startCost, float endCost, int userId) {
        return em.createNamedQuery(Course.BETWEEN_COST, Course.class)
                .setParameter("userId", userId)
                .setParameter("startCost", startCost)
                .setParameter("endCost", endCost)
                .getResultList();
    }
}
