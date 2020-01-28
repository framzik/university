package ru.university.web.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.university.model.Course;

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
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@RequestParam("id") Integer id,
                               @RequestParam("name") String name,
                               @RequestParam("number") int number,
                               @RequestParam("cost") float cost) {
        Course course = new Course(id, name, number, cost);
        if (course.isNew()) {
            super.create(course);
        }
    }

    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getBetween(@RequestParam("startCost") float startCost,
                                   @RequestParam("endCost") float endCost) {
        return super.getBetween(startCost, endCost);
    }
}
