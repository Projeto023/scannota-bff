package com.example.desafiobackenditarc.clients;

import com.example.desafiobackenditarc.dto.notification.UserNotificationDTO;

public interface MockClientService {
    void notifyUser(UserNotificationDTO userNotificationDTO, Integer userId);
}
