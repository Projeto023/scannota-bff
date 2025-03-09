package com.example.desafiobackenditarc.integration;

import com.example.desafiobackenditarc.config.IntegrationTest;
import com.example.desafiobackenditarc.controller.NotificationController;
import com.example.desafiobackenditarc.controller.UserController;
import com.example.desafiobackenditarc.controller.advicer.ApiControllerAdvicer;
import com.example.desafiobackenditarc.dto.request.NotifyForecastRequestDTO;
import com.example.desafiobackenditarc.dto.request.ScheduleForecastRequestDTO;
import com.example.desafiobackenditarc.enums.NotificationStatusEnum;
import com.example.desafiobackenditarc.model.Notification;
import com.example.desafiobackenditarc.model.User;
import com.example.desafiobackenditarc.repository.NotificationRepository;
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

import java.util.Date;

import static com.example.desafiobackenditarc.utils.StringUtils.toJson;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class NotificationControllerTest {
    @Autowired
    private NotificationController controller;

    @Autowired
    private NotificationRepository notificationRepository;
    private MockMvc mockMvc;

    private String controllerUrl = "/api/v1/notify";

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new ApiControllerAdvicer())
                .build();
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql", "classpath:sql/notification_direct_success_test.sql"})
    void successDirectNotification() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(controllerUrl.concat("/forecast"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(NotifyForecastRequestDTO.builder()
                                .cityName("city-successDirectNotification")
                                .build()))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.success")
                        .value(true))
                .andExpect(jsonPath("$.message")
                        .value("success"));

        final Notification notification = notificationRepository.findFirstByOrderByIdDesc();

        Assertions.assertEquals("city-successDirectNotification", notification.getCity());
        Assertions.assertEquals(NotificationStatusEnum.PROCESSED.getDescription(), notification.getStatus());
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql", "classpath:sql/notification_scheduled_success_test.sql"})
    void successScheduledNotification() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(controllerUrl.concat("/forecast/scheduled/999"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.success")
                        .value(true))
                .andExpect(jsonPath("$.message")
                        .value("success"));

        final Notification notification = notificationRepository.findFirstByOrderByIdDesc();

        Assertions.assertEquals("city-successScheduledNotification", notification.getCity());
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql", "classpath:sql/notification_scheduled_success_test.sql"})
    void successScheduleNotification() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(controllerUrl.concat("/schedule"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(ScheduleForecastRequestDTO.builder()
                                .cityName("city-successScheduleNotification")
                                .notificationDate(new Date())
                                .build()))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.success")
                        .value(true))
                .andExpect(jsonPath("$.message")
                        .value("success"));

        final Notification notification = notificationRepository.findFirstByOrderByIdDesc();

        Assertions.assertEquals("city-successScheduleNotification", notification.getCity());
        Assertions.assertEquals(NotificationStatusEnum.PENDING.getDescription(), notification.getStatus());
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql"})
    void errorNoUserFromCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(controllerUrl.concat("/forecast"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(NotifyForecastRequestDTO.builder()
                                .cityName("city-successDirectNotification")
                                .build()))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success")
                        .value(false))
                .andExpect(jsonPath("$.message")
                        .value("No users from this city to be notified"));
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql", "classpath:sql/notification_city_not_exist_test.sql"})
    void errorCityNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(controllerUrl.concat("/forecast"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(NotifyForecastRequestDTO.builder()
                                .cityName("not_exist")
                                .build()))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success")
                        .value(false))
                .andExpect(jsonPath("$.message")
                        .value("Error getting city details on CPTEC"));
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql", "classpath:sql/notification_found_wrong_city_test.sql"})
    void errorFoundWrongCity() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(controllerUrl.concat("/forecast"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(NotifyForecastRequestDTO.builder()
                                .cityName("wrong_city")
                                .build()))
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success")
                        .value(false))
                .andExpect(jsonPath("$.message")
                        .value("No matching city found on CPTEC"));
    }

    @Test
    @Sql({"classpath:sql/cleanup_script.sql"})
    void errorNotificationDoesNotExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(controllerUrl.concat("/forecast/scheduled/50"))
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("utf-8"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.success")
                        .value(false))
                .andExpect(jsonPath("$.message")
                        .value("Notification does not exist with id: 50"));
    }
}
