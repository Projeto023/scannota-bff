package com.example.desafiobackenditarc.service.impl;

import com.example.desafiobackenditarc.exception.EntityNotFoundException;
import com.example.desafiobackenditarc.model.User;
import com.example.desafiobackenditarc.repository.UserRepository;
import com.example.desafiobackenditarc.service.UserPreferencesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserPreferencesServiceImpl implements UserPreferencesService {
    final UserRepository userRepository;

    @Override
    public void changeUserAllowNotificationSetting(Integer userId, boolean allowNotification)
            throws EntityNotFoundException {
        final Optional<User> user = userRepository.findById(userId);

        if(user.isEmpty()) {
            final String message = "[OptOutUserService] user id does not exist: " + userId;
            log.error(message);
            throw new EntityNotFoundException(message);
        }

        final User userToBeUpdated = user.get();

        userToBeUpdated.setAllowNotifications(allowNotification);
        userRepository.save(userToBeUpdated);
    }
}
