package ru.university.web.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.university.model.Course;
import ru.university.model.User;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping(value =CourseRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CourseRestController extends AbstractCourseController {
    public static final String REST_URL = "/rest/courses";

    @Override
    @GetMapping
    public List<Course> getAll() {
        return super.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public Course get(@PathVariable int id) {
        return super.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> createWithLocation(@RequestBody Course course) {
        Course created = super.create(course);
        URI uriOfNewCourse = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL+"/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewCourse).body(created);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        super.delete(id);
    }

    @Override
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody Course course, @PathVariable int id) {
        super.update(course, id);
    }

    @Override
    @GetMapping("/between")
    public List<Course> getBetween(@RequestParam float startCost,@RequestParam float endCost) {
        return super.getBetween(startCost, endCost);
    }


}
