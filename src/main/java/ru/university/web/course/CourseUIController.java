package ru.university.web.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.university.model.Course;
import ru.university.util.ValidationUtil;

import javax.validation.Valid;
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
    public ResponseEntity<String> createOrUpdate(@Valid Course course, BindingResult result ) {
        if (result.hasErrors()) {
            return ValidationUtil.getErrorResponse(result);
        }
        if (course.isNew()) {
            super.create(course);
        } else{
            super.update(course, course.id());
        }
        return ResponseEntity.ok().build();
    }

    @Override
    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getBetween(@RequestParam float startCost,
                                   @RequestParam float endCost) {
        return super.getBetween(startCost, endCost);
    }
}
