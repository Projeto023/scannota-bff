package com.example.desafiobackenditarc.dto.notification;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserNotificationDTO {
    private String cityName;
    private List<WeatherForecast> weatherForecast;
    private WaveForecast waveForecast;
}
