package ru.university.web.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.university.TestUtil;
import ru.university.UserTestData;
import ru.university.model.Role;
import ru.university.model.User;
import ru.university.service.UserService;
import ru.university.util.exception.ErrorType;
import ru.university.util.exception.NotFoundException;
import ru.university.web.AbstractControllerTest;
import ru.university.web.json.JsonUtil;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static ru.university.TestUtil.readFromJson;
import static ru.university.TestUtil.userHttpBasic;
import static ru.university.UserTestData.*;

class AdminRestControllerTest extends AbstractControllerTest {
    public static final String REST_URL = AdminRestController.REST_URL + "/";

    @Autowired
    private UserService userService;

    @Test
    void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + PROFESSOR_ID)
                .with(userHttpBasic(GRIGOREV)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(GRIGOREV));
    }

    @Test
    void getNotFound() throws Exception {
        perform(doGet(REST_URL,100000).basicAuth(GRIGOREV))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }

    @Test
    void getByMail() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + "by?email=" + GRIGOREV.getEmail())
                .with(userHttpBasic(GRIGOREV)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(contentJson(GRIGOREV));
    }


    @Test
    void delete() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + STUDENT_ID)
                .with(userHttpBasic(GRIGOREV)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> userService.get(STUDENT_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL+1000)
        .with(userHttpBasic(GRIGOREV)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getUnAuth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getForbidden() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(SAVCHYK)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getAll() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(GRIGOREV)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(contentJson(BELYALOV, GRIGOREV, NOVOGILOV, SAVCHYK, STAROSTENKO, YAMCHEKOV));
    }

    @Test
    void update() throws Exception {
        User updated = UserTestData.getUpdated();
        perform(doPut(REST_URL, STUDENT_ID).jsonUserWithPassword(updated).basicAuth(GRIGOREV))
                .andExpect(status().isNoContent());

        assertMatch(userService.get(STUDENT_ID), updated);
    }

    @Test
    void createWithLocation() throws Exception {
        User newUser = UserTestData.getNew();
        ResultActions action = perform(doPost(REST_URL).jsonUserWithPassword(newUser).basicAuth(GRIGOREV))
                .andExpect(status().isCreated());

        User created = TestUtil.readFromJson(action, User.class);
        Integer newId = created.getId();
        newUser.setId(newId);
        assertMatch(created, newUser);
        assertMatch(userService.get(newId), newUser);
    }

    @Test
    void enable() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(REST_URL + STUDENT_ID)
                .param("enabled", "false")
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(YAMCHEKOV)))
                .andDo(print())
                .andExpect(status().isNoContent());

        assertFalse(userService.get(STUDENT_ID).isEnabled());
    }

    @Test
    void createInvalid() throws Exception {
        User expected = new User(null, null, "", "newPass", "Stroibat 18", Role.ROLE_STUDENT, Role.ROLE_PROFESSOR);
        perform(doPost(REST_URL).jsonBody(expected).basicAuth(GRIGOREV))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
                .andDo(print());
    }

    @Test
    void updateInvalid() throws Exception {
        User updated = new User(SAVCHYK);
        updated.setName("");
        perform(doPut(REST_URL, SAVCHYK.getId()).jsonBody(updated).basicAuth(GRIGOREV))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print())
                .andExpect(jsonPath("$.type").value(ErrorType.VALIDATION_ERROR.name()))
                .andDo(print());
    }

}