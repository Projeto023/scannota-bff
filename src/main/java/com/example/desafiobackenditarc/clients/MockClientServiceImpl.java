package com.example.desafiobackenditarc.clients;

import com.example.desafiobackenditarc.dto.notification.UserNotificationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MockClientServiceImpl implements MockClientService {
    final MockFeignClient mockFeignClient;

    @Override
    public void notifyUser(UserNotificationDTO userNotificationDTO, Integer userId) {
        mockFeignClient.notifyUser();
        log.info("[MockClientService] MOCK OK");
    }
}
