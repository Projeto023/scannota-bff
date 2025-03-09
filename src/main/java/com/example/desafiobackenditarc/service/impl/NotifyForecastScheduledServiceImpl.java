package com.example.desafiobackenditarc.service.impl;

import com.example.desafiobackenditarc.dto.request.NotifyForecastRequestDTO;
import com.example.desafiobackenditarc.dto.response.NotifyForecastResponseDTO;
import com.example.desafiobackenditarc.enums.NotificationStatusEnum;
import com.example.desafiobackenditarc.exception.CPTECException;
import com.example.desafiobackenditarc.exception.DesafioBackendItArcApiException;
import com.example.desafiobackenditarc.exception.EntityNotFoundException;
import com.example.desafiobackenditarc.model.Notification;
import com.example.desafiobackenditarc.repository.NotificationRepository;
import com.example.desafiobackenditarc.service.NotifyForecastScheduledService;
import com.example.desafiobackenditarc.service.NotifyForecastService;
import com.example.desafiobackenditarc.service.SendNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotifyForecastScheduledServiceImpl implements NotifyForecastScheduledService {
    final NotificationRepository notificationRepository;
    final SendNotificationService sendNotificationService;

    @Override
    public NotifyForecastResponseDTO process(final Integer notificationId)
            throws DesafioBackendItArcApiException, EntityNotFoundException, CPTECException {
        final Optional<Notification> notificationOptional = notificationRepository.findById(
                notificationId);

        if(notificationOptional.isEmpty()) {
            final String errorMsg = "[NotifyForecastService] Notification does not exist with id: " +
                    notificationId;

            log.error(errorMsg);
            throw new EntityNotFoundException("Notification does not exist with id: " + notificationId);
        }

        final Notification notification = notificationOptional.get();

        log.info("[NotifyForecastService] Sending scheduled notification id: {}", notification.getId());
        return sendNotificationService.notifyUsers(notification.getCity());
    }
}
