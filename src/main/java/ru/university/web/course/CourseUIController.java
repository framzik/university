package ru.university.web.course;

import com.sun.istack.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.university.View;
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
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Course get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void createOrUpdate(@Validated(View.Web.class) Course course) {
        if (course.isNew()) {
            super.create(course);
        } else {
            super.update(course, course.id());
        }
    }

    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getBetween(@RequestParam @Nullable float startCost,
                                   @RequestParam @Nullable float endCost) {
        return super.getBetween(startCost, endCost);
    }
}
