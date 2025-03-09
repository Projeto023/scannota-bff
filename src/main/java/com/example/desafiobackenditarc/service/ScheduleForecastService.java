package com.example.desafiobackenditarc.service;

import com.example.desafiobackenditarc.dto.request.ScheduleForecastRequestDTO;

public interface ScheduleForecastService {
    void process(ScheduleForecastRequestDTO request);
}
