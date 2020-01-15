package ru.university.web;

import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.university.UserTestData.YAMCHEKOV;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void root() {
    }

    @Test
    public void getUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(6)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(1)),
                                hasProperty("name", is(YAMCHEKOV.getName()))
                        )
                )));
    }


    @Test
    public void setUser() {
    }

    @Test
    public void getCourses() {
    }
}