package com.example.desafiobackenditarc.service;

import com.example.desafiobackenditarc.dto.request.NotifyForecastRequestDTO;
import com.example.desafiobackenditarc.dto.response.NotifyForecastResponseDTO;
import com.example.desafiobackenditarc.exception.CPTECException;
import com.example.desafiobackenditarc.exception.DesafioBackendItArcApiException;
import com.example.desafiobackenditarc.exception.EntityNotFoundException;

public interface NotifyForecastService {
    NotifyForecastResponseDTO process(NotifyForecastRequestDTO request)
            throws DesafioBackendItArcApiException, EntityNotFoundException, CPTECException;
}
