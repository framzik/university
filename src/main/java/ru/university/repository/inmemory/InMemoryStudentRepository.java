package ru.university.repository.inmemory;

import ru.university.model.Student;
import ru.university.repository.StudentRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class InMemoryStudentRepository implements StudentRepository {
    private Map<Integer, Student> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    public static final List<Student> STUDENTS = Arrays.asList(
            new Student(null,"Ямчеков Н.А", "ул. Стокгольма 28", "fr@ya.ru", 111, 4.7f),
            new Student(null, "Белялов Л.Э", "ул. Белогвардейцев 38", "fr@yahoo.com", 112, 4.5f),
            new Student(null,"Новожилов Э.А.", "ул. Васильков 34", "fr@gmail.com", 113, 5.0f),
            new Student(null,"Савчук А.И", "ул. Самойловой 33", "fr@mail.ru", 114, 3.5f)
    );

    {
        STUDENTS.forEach(this::save);
    }

    @Override
    public Student save(Student student) {
        if(student.isNew()){
            student.setId(counter.incrementAndGet());
            repository.put(student.getId(),student);
            return student;
        }
        return repository.computeIfPresent(student.getId(), (id,oldStudent)->student);
    }

    @Override
    public boolean delete(int id) {
        return repository.remove(id)!=null;
    }

    @Override
    public Student get(int id) {
        return repository.get(id);
    }

    @Override
    public Student getByEmail(String email) {
       for(Map.Entry<Integer,Student> st: repository.entrySet() ){
           if(st.getValue().toString().contains(email))
               return get(st.getKey());
       }
        return null;
    }

    @Override
    public Collection<Student> getAll() {
        return repository.values();
    }
}
