package com.example.desafiobackenditarc.service.impl;

import com.example.desafiobackenditarc.dto.request.ScheduleForecastRequestDTO;
import com.example.desafiobackenditarc.enums.NotificationStatusEnum;
import com.example.desafiobackenditarc.model.Notification;
import com.example.desafiobackenditarc.repository.NotificationRepository;
import com.example.desafiobackenditarc.service.ScheduleForecastService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleForecastServiceImpl implements ScheduleForecastService {
    final NotificationRepository notificationRepository;

    @Override
    public void process(ScheduleForecastRequestDTO requestDTO) {
        final Notification notification =
                Notification.builder().city(requestDTO.getCityName()).notificationDate(requestDTO.getNotificationDate())
                        .status(NotificationStatusEnum.PENDING.getDescription()).build();
        notificationRepository.save(notification);
        log.info("[NotifyForecastService] Notification scheduled with success: {}", notification);
    }
}
