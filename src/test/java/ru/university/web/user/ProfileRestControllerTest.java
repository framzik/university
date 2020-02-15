package ru.university.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
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
import static ru.university.TestUtil.readFromJson;
import static ru.university.TestUtil.userHttpBasic;
import static ru.university.UserTestData.*;
import static ru.university.web.user.ProfileRestController.REST_URL;

class ProfileRestControllerTest extends AbstractControllerTest {

    @Autowired
    private UserService userService;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(YAMCHEKOV)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(YAMCHEKOV));
    }

    @Test
    void getUnAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL)
                .with(userHttpBasic(YAMCHEKOV)))
                .andExpect(status().isNoContent());
        assertMatch(userService.getAll(), BELYALOV, GRIGOREV, NOVOGILOV, SAVCHYK, STAROSTENKO);
    }

    @Test
    void update() throws Exception {
        UserTo updated = new UserTo(null,"newName","newemail@ya.ru","newPassword","newAddress");
        mockMvc.perform(MockMvcRequestBuilders.put(REST_URL).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(YAMCHEKOV))
                .content(JsonUtil.writeValue(updated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertMatch(userService.get(STUDENT_ID), Util.updateFromTo(new User(YAMCHEKOV),updated));
    }

    @Test
    void register() throws Exception {
        UserTo newTo = new UserTo(null, "newName", "newemail@ya.ru", "newPassword", "Строителей 10");
        User newUser = Util.createNewUserFromTo(newTo);

        ResultActions action = mockMvc.perform(MockMvcRequestBuilders.post(REST_URL+"/register").contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newTo)))
                .andDo(print())
                .andExpect(status().isCreated());

        User created = readFromJson(action, User.class);
        Integer newId = created.getId();
        newUser.setId(newId);
        assertMatch(created,newUser);
        assertMatch(userService.get(newId),newUser);
    }
}