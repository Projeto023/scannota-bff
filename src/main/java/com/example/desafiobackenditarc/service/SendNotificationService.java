package com.example.desafiobackenditarc.service;

import com.example.desafiobackenditarc.dto.response.NotifyForecastResponseDTO;
import com.example.desafiobackenditarc.exception.CPTECException;
import com.example.desafiobackenditarc.exception.DesafioBackendItArcApiException;
import com.example.desafiobackenditarc.exception.EntityNotFoundException;

public interface SendNotificationService {
    NotifyForecastResponseDTO notifyUsers(final String cityName)
            throws EntityNotFoundException, CPTECException, DesafioBackendItArcApiException;
}
