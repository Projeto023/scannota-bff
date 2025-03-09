package com.example.desafiobackenditarc.service;

import com.example.desafiobackenditarc.exception.EntityNotFoundException;

public interface UserPreferencesService {
    void changeUserAllowNotificationSetting(Integer userId, boolean allowNotification) throws EntityNotFoundException;
}
