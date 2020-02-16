package ru.university.web.course;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.university.model.Course;

import javax.validation.Valid;
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
    public ResponseEntity<Course> createWithLocation(@Valid @RequestBody Course course) {
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
    public void update(@Valid @RequestBody Course course, @PathVariable int id) {
        super.update(course, id);
    }


    @GetMapping("/filter")
    public List<Course> getBetween(@RequestParam Float startCost,@RequestParam Float endCost) {
        if(startCost==null){
            startCost=0f;
        }
        if(endCost==null){
            endCost=250000f;
        }
        return super.getBetween(startCost, endCost);
    }


}
