package ru.university.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.university.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.university.UserTestData.GRIGOREV;
import static ru.university.UserTestData.PROFESSOR_ID;
import static ru.university.web.json.JsonUtil.writeIgnoreProps;

class AdminRestControllerTest extends AbstractControllerTest {
    public static  final String REST_URL =AdminRestController.REST_URL + "/";
    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + PROFESSOR_ID))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(writeIgnoreProps(GRIGOREV, "registered")));
    }
}