package com.example.desafiobackenditarc.service.impl;

import com.example.desafiobackenditarc.dto.request.NotifyForecastRequestDTO;
import com.example.desafiobackenditarc.dto.response.NotifyForecastResponseDTO;
import com.example.desafiobackenditarc.enums.NotificationStatusEnum;
import com.example.desafiobackenditarc.exception.CPTECException;
import com.example.desafiobackenditarc.exception.DesafioBackendItArcApiException;
import com.example.desafiobackenditarc.exception.EntityNotFoundException;
import com.example.desafiobackenditarc.model.Notification;
import com.example.desafiobackenditarc.repository.NotificationRepository;
import com.example.desafiobackenditarc.service.SendNotificationService;
import com.example.desafiobackenditarc.service.NotifyForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotifyForecastServiceImpl implements NotifyForecastService {
    final NotificationRepository notificationRepository;
    final SendNotificationService sendNotificationService;

    @Override
    public NotifyForecastResponseDTO process(final NotifyForecastRequestDTO request)
            throws DesafioBackendItArcApiException, EntityNotFoundException, CPTECException {
        final String cityName = request.getCityName();
        final Notification notification = Notification.builder().city(cityName).build();
        notification.setStatus(NotificationStatusEnum.PENDING.getDescription());
        log.info("[NotifyForecastService] Sending not scheduled notification: {}", notification);

        try {
            final NotifyForecastResponseDTO response = sendNotificationService.notifyUsers(cityName);
            notification.setNotificationDate(new Date());
            notification.setStatus(NotificationStatusEnum.PROCESSED.getDescription());
            notificationRepository.save(notification);
            return response;
        } catch (Exception e) {
            log.error("[NotifyForecastService] Error on notificating with error: {}", e.getMessage());
            notification.setStatus(NotificationStatusEnum.ERROR.getDescription());
            notification.setErrorMessage(e.getMessage());
            notificationRepository.save(notification);
            throw e;
        }
    }
}
