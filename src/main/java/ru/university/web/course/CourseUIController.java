package ru.university.web.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.university.model.Course;
import ru.university.to.CourseTo;

import java.util.List;

@RestController
@RequestMapping("/ajax/profile/courses")
public class CourseUIController extends AbstractCourseController {
    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getAll() {
        return super.getAll();
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course get(@PathVariable("id") int id) {
        return super.get(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(CourseTo courseTo) {
        if (courseTo.isNew()) {
            super.create(courseTo);
        } else{
            super.update(courseTo, courseTo.id());
        }
    }

    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getBetween(@RequestParam float startCost,
                                   @RequestParam float endCost) {
        return super.getBetween(startCost, endCost);
    }
}
