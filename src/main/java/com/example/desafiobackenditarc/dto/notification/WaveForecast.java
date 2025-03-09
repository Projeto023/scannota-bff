package com.example.desafiobackenditarc.dto.notification;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WaveForecast {
    private String name;
    private String state;
    private Period morning;
    private Period afternoon;
    private Period night;
}
