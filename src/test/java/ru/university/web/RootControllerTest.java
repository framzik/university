package ru.university.web;

import org.assertj.core.matcher.AssertionMatcher;
import org.junit.jupiter.api.Test;
import ru.university.model.Course;
import ru.university.model.User;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.university.CourseTestData.*;
import static ru.university.UserTestData.*;

public class RootControllerTest extends AbstractControllerTest {

    @Test
     void getUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users",
                        new AssertionMatcher<List<User>>() {
                            @Override
                            public void assertion(List<User> actual) throws AssertionError {
                                assertMatch(actual, BELYALOV, GRIGOREV, NOVOGILOV, SAVCHYK, STAROSTENKO, YAMCHEKOV);
                            }
                        }
                ));
    }


    @Test
    void testCourses() throws Exception {
        mockMvc.perform(get("/courses"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("courses"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/courses.jsp"));
    }
}