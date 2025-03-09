package com.example.desafiobackenditarc.dto.notification;

import lombok.Builder;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@Data
@Builder
public class WeatherForecast {
    private String day;
    private String weather;
    private Integer maxTemperature;
    private Integer minTemperature;
    private Double uvIndex;
}
