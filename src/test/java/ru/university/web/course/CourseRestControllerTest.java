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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.university.CourseTestData.*;
import static ru.university.TestUtil.readFromJson;
import static ru.university.TestUtil.userHttpBasic;
import static ru.university.UserTestData.*;

class CourseRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = "/rest/courses/";

    @Autowired
    private CourseService courseService;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + STUDENT_COURSE_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(COURSE_1));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + STUDENT_COURSE_ID))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertThrows(NotFoundException.class, () -> courseService.get(STUDENT_COURSE_ID, STUDENT_ID));
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
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL + STUDENT_COURSE_ID).contentType(MediaType.APPLICATION_JSON).content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent())
                .andDo(print());

        assertMatch(courseService.get(STUDENT_COURSE_ID, STUDENT_ID),  Util.updateFromTo(COURSE_1, updated));
    }

    @Test
    void createWithLocation() throws Exception {
        Course newCourse = CourseTestData.getNew();
        ResultActions actions = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(JsonUtil.writeValue(newCourse)))
                .andExpect(status().isCreated());

        Course created = readFromJson(actions,Course.class);
        Integer newId  = created.getId();
        newCourse.setId(newId);
        assertMatch(created,newCourse);
        assertMatch(courseService.get(newId,STUDENT_ID),newCourse);
    }


    @Test
    void getBetween() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "filter?startCost=15000&endCost=16000"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(contentJson(COURSE_2, COURSE_1));
    }
}