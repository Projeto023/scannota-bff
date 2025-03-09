package com.example.desafiobackenditarc.integration;

import com.example.desafiobackenditarc.config.IntegrationTest;
import com.example.desafiobackenditarc.controller.UserController;
import com.example.desafiobackenditarc.controller.advicer.ApiControllerAdvicer;
import com.example.desafiobackenditarc.model.User;
import com.example.desafiobackenditarc.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class UserControllerTest {
    @Autowired
    private UserController controller;

    @Autowired
    private UserRepository userRepository;
    private MockMvc mockMvc;

    private String controllerUrl = "/api/v1/user";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ApiControllerAdvicer())
                .build();
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql", "classpath:sql/user_allow_notification_test.sql"})
    void successOptin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(controllerUrl.concat("/optin/1"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.success")
                        .value(true))
                .andExpect(jsonPath("$.message")
                        .value("success"));

        final User user = userRepository.findById(1).get();

        Assertions.assertEquals(true, user.getAllowNotifications());
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql", "classpath:sql/user_allow_notification_test.sql"})
    void sucessOptout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(controllerUrl.concat("/optout/1"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.success")
                        .value(true))
                .andExpect(jsonPath("$.message")
                        .value("success"));

        final User user = userRepository.findById(1).get();

        Assertions.assertEquals(false, user.getAllowNotifications());
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql"})
    void errorUserNotFoundOptin() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(controllerUrl.concat("/optin/1"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql"})
    void errorUserNotFoundOptout() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch(controllerUrl.concat("/optout/1"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is4xxClientError());
    }
}
