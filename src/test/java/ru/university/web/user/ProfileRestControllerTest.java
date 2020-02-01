package ru.university.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.university.UserTestData;
import ru.university.model.User;
import ru.university.service.UserService;
import ru.university.to.UserTo;
import ru.university.util.Util;
import ru.university.web.AbstractControllerTest;
import ru.university.web.json.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.university.UserTestData.*;
import static ru.university.web.user.ProfileRestController.REST_URL;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(YAMCHEKOV));
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), BELYALOV, GRIGOREV, NOVOGILOV, SAVCHYK, STAROSTENKO);
    }

    @Test
    void update() throws Exception {
        UserTo updated = new UserTo(null,"newName","newemail@ya.ru","newPassword","newAddress");
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(userService.get(STUDENT_ID), Util.updateFromTo(new User(YAMCHEKOV),updated));
    }
}