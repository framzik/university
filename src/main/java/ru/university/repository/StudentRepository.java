package ru.university.repository;

import org.springframework.stereotype.Repository;
import ru.university.model.Student;

import java.util.Collection;

@Repository
public interface StudentRepository extends UserBaseRepository<Student>  {

//    Student save(Student entity);
//
//
//    boolean delete(int id);
//
//
   Student get(int id);
//
//
//    Student getByEmail(String email);
//
//
//    Collection<Student> getAll();
}
