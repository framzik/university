package ru.university.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.university.model.Course;
import ru.university.repository.CourseRepository;

import java.util.List;
@Repository
public class DataJpaCourseRepository implements CourseRepository {

   @Autowired
   CrudCourseRepository crudCourseRepository;
   @Autowired
   CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Course save(Course course, int userId) {
        if (!course.isNew() && get(course.getId(), userId) == null) {
            return null;
        }
        course.setUser(crudUserRepository.getOne(userId));
        return crudCourseRepository.save(course);
    }

    @Override
    public boolean delete(int id, int userId) {
        return crudCourseRepository.delete(id,userId)!=0;
    }

    @Override
    public Course get(int id, int userId) {
        return crudCourseRepository.findById(id).filter(course -> course.getUser().getId()==userId).orElse(null);
    }

    @Override
    public Course getByName(String name, int userId) {
        return crudCourseRepository.getByName(name,userId);
    }

    @Override
    public List<Course> getAll(int userId) {
        return crudCourseRepository.getAll(userId);
    }

    @Override
    public List<Course> getBetweenCost(float startCost, float endCost, int userId) {
        return crudCourseRepository.getBetweenCost(startCost,endCost,userId);
    }
}
