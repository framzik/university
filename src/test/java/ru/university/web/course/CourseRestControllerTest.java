package ru.university.web.course;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.university.CourseTestData;
import ru.university.model.Course;
import ru.university.service.CourseService;
import ru.university.to.CourseTo;
import ru.university.util.Util;
import ru.university.util.exception.NotFoundException;
import ru.university.web.AbstractControllerTest;
import ru.university.web.json.JsonUtil;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.university.CourseTestData.assertMatch;
import static ru.university.CourseTestData.contentJson;
import static ru.university.CourseTestData.*;
import static ru.university.TestUtil.readFromJson;
import static ru.university.TestUtil.userHttpBasic;
import static ru.university.UserTestData.*;
import static ru.university.util.exception.ErrorType.VALIDATION_ERROR;

class CourseRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = "/rest/courses/";


    @Autowired
    private CourseService courseService;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + 6)
                .with(userHttpBasic(SAVCHYK)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(COURSE_6));
    }

    @Test
    void getNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + PROFESSOR_COURSE_ID)
                .with(userHttpBasic(SAVCHYK)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getUnauth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + STUDENT_COURSE_ID)
                .with(userHttpBasic(YAMCHEKOV)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> courseService.get(STUDENT_COURSE_ID, STUDENT_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + PROFESSOR_COURSE_ID)
                .with(userHttpBasic(SAVCHYK)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(YAMCHEKOV)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(COURSE_2, COURSE_3, COURSE_1));
    }

    @Test
    void update() throws Exception {
        CourseTo updated = new CourseTo(null, "newName", 9966, 15200f);
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + STUDENT_COURSE_ID).with(userHttpBasic(YAMCHEKOV)).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent())
                .andDo(print());

        assertMatch(courseService.get(STUDENT_COURSE_ID, STUDENT_ID), Util.updateFromTo(COURSE_1, updated));
    }

    @Test
    void createWithLocation() throws Exception {
        Course newCourse = CourseTestData.getNew();
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(YAMCHEKOV))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeValue(newCourse)))
                .andExpect(status().isCreated());

        Course created = readFromJson(actions, Course.class);
        Integer newId = created.getId();
        newCourse.setId(newId);
        assertMatch(created, newCourse);
        assertMatch(courseService.get(newId, STUDENT_ID), newCourse);
    }


    @Test
    void filter() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "filter?startCost=15000&endCost=16000").with(userHttpBasic(YAMCHEKOV)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(COURSE_2, COURSE_1));
    }

    @Test
    void filterAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "filter?startCost=&endCost=").with(userHttpBasic(YAMCHEKOV)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(COURSE_2, COURSE_3, COURSE_1));
    }

    @Test
    void createInvalid() throws Exception {
        Course invalid = new Course(null, null, 25554, 200f);
        perform(doPost(REST_URL).jsonBody(invalid).basicAuth(GRIGOREV))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateInvalid() throws Exception {
        Course invalid = new Course(STUDENT_COURSE_ID, null, 0, 6000);
        perform(doPut(REST_URL, STUDENT_COURSE_ID).jsonBody(invalid).basicAuth(SAVCHYK))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

    @Test
    void updateHtmlUnsafe() throws Exception {
        Course invalid = new Course(STUDENT_COURSE_ID, "<script>alert(123)</script>", 12, 200);
        perform(doPut(REST_URL,STUDENT_COURSE_ID).jsonBody(invalid).basicAuth(SAVCHYK))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity())
                .andExpect(errorType(VALIDATION_ERROR))
                .andDo(print());
    }

}