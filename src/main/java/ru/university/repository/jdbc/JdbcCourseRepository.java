package ru.university.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import ru.university.model.Course;
import ru.university.repository.CourseRepository;

import java.util.List;
@Repository
public class JdbcCourseRepository implements CourseRepository {
    private static final BeanPropertyRowMapper<Course> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Course.class);

    private final JdbcTemplate jdbcTemplate;

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private final SimpleJdbcInsert insertCourse;

    @Autowired
    public JdbcCourseRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertCourse = new SimpleJdbcInsert(jdbcTemplate);
        this.insertCourse.withTableName("university_courses");
        this.insertCourse.usingGeneratedKeyColumns("id");

        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    @Override
    public Course save(Course course, int userId) {
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", course.getId())
                .addValue("user_id", userId)
                .addValue("name", course.getName())
                .addValue("number", course.getNumber())
                .addValue("cost", course.getCost());

        if (course.isNew()) {
            Number newKey = insertCourse.executeAndReturnKey(map);
            course.setId(newKey.intValue());
        } else if (namedParameterJdbcTemplate.update(
                "UPDATE university_courses SET name=:name, number=:number, cost=:cost WHERE id=:id AND user_id=:user_id ", map) == 0) {
            return null;
        }
        return course;
    }

    @Override
    public boolean delete(int id, int userId) {
        return false;
    }

    @Override
    public Course get(int id, int userId) {
        return null;
    }

    @Override
    public Course getByName(String name, int userId) {
        return null;
    }

    @Override
    public List<Course> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM university_courses WHERE user_id=? ORDER BY name", ROW_MAPPER,userId);
    }

    @Override
    public List<Course> getBetweenCost(float startCost, float endCost, int userId) {
        return null;
    }
}
