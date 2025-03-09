package com.example.desafiobackenditarc.service.impl;

import com.example.desafiobackenditarc.clients.CPTECApiService;
import com.example.desafiobackenditarc.clients.MockClientService;
import com.example.desafiobackenditarc.dto.cptec.CityForecastDTO;
import com.example.desafiobackenditarc.dto.cptec.CityListDTO;
import com.example.desafiobackenditarc.dto.cptec.WaveForecastDTO;
import com.example.desafiobackenditarc.dto.notification.Period;
import com.example.desafiobackenditarc.dto.notification.UserNotificationDTO;
import com.example.desafiobackenditarc.dto.notification.WaveForecast;
import com.example.desafiobackenditarc.dto.notification.WeatherForecast;
import com.example.desafiobackenditarc.dto.response.NotifyForecastResponseDTO;
import com.example.desafiobackenditarc.dto.response.NotifyForecastResponseUserErrorDTO;
import com.example.desafiobackenditarc.exception.CPTECException;
import com.example.desafiobackenditarc.exception.DesafioBackendItArcApiException;
import com.example.desafiobackenditarc.exception.EntityNotFoundException;
import com.example.desafiobackenditarc.model.User;
import com.example.desafiobackenditarc.repository.NotificationRepository;
import com.example.desafiobackenditarc.repository.UserRepository;
import com.example.desafiobackenditarc.service.SendNotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.example.desafiobackenditarc.utils.StringUtils.compareStrings;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendNotificationServiceImpl implements SendNotificationService {
    final NotificationRepository notificationRepository;
    final UserRepository userRepository;
    final CPTECApiService cptecApiService;
    final MockClientService mockClientService;

    public NotifyForecastResponseDTO notifyUsers(final String cityName)
            throws EntityNotFoundException, CPTECException, DesafioBackendItArcApiException {
        final List<User> usersToBeNotified = userRepository.findByCityName(cityName);

        if (usersToBeNotified.isEmpty()) {
            log.error("[NotifyForecastService] No users from this city to be notified");
            throw new EntityNotFoundException("No users from this city to be notified");
        }

        final CityListDTO cityList = cptecApiService.getCityList(cityName);

        if (Objects.isNull(cityList.getCities()) || cityList.getCities().isEmpty()) {
            log.error("[NotifyForecastService] Error getting city details on CPTEC. City: {}", cityName);
            throw new EntityNotFoundException("Error getting city details on CPTEC");
        }

        final Optional<CityListDTO.CityDTO> optionalCity = cityList.getCities().stream()
                .filter(cityDTO -> compareStrings(cityDTO.getName(), cityName))
                .findFirst();

        if (optionalCity.isEmpty()) {
            log.error("[NotifyForecastService] No matching city found on CPTEC. City: {}", cityName);
            throw new EntityNotFoundException("No matching city found on CPTEC");
        }

        final CityListDTO.CityDTO city = optionalCity.get();

        final CityForecastDTO cityForecast = cptecApiService.getCityForecast(city.getId());
        final WaveForecastDTO waveForecast = cptecApiService.getWaveForecast(city.getId(), 0);

        final UserNotificationDTO notificationDTO = mapToNotificationDTO(cityForecast, waveForecast, city);

        final NotifyForecastResponseDTO response = notifyAsync(usersToBeNotified, notificationDTO);

        log.info("Notification sent to city: {}, notification: {}", cityName, notificationDTO);

        return response;
    }

    private UserNotificationDTO mapToNotificationDTO(CityForecastDTO cityForecast, WaveForecastDTO waveForecast,
                                                     CityListDTO.CityDTO city) {
        return UserNotificationDTO.builder().weatherForecast(cityForecast.getForecasts().stream()
                        .map(forecast -> WeatherForecast.builder().day(forecast.getDay()).weather(forecast.getWeather())
                                .maxTemperature(forecast.getMaxTemperature()).minTemperature(forecast.getMinTemperature())
                                .uvIndex(forecast.getUvIndex()).build()).collect(Collectors.toList())).waveForecast(
                        "undefined".equals(waveForecast.getName()) ? null :
                                WaveForecast.builder().name(city.getName()).state(city.getState()).morning(
                                                Period.builder().day(waveForecast.getMorning().getDay())
                                                        .agitation(waveForecast.getMorning().getAgitation())
                                                        .height(waveForecast.getMorning().getHeight())
                                                        .direction(waveForecast.getMorning().getDirection())
                                                        .wind(waveForecast.getMorning().getWind())
                                                        .windDirection(waveForecast.getMorning().getWindDirection()).build()).afternoon(
                                                Period.builder().day(waveForecast.getAfternoon().getDay())
                                                        .agitation(waveForecast.getAfternoon().getAgitation())
                                                        .height(waveForecast.getAfternoon().getHeight())
                                                        .direction(waveForecast.getAfternoon().getDirection())
                                                        .wind(waveForecast.getAfternoon().getWind())
                                                        .windDirection(waveForecast.getAfternoon().getWindDirection()).build())
                                        .night(Period.builder().day(waveForecast.getNight().getDay())
                                                .agitation(waveForecast.getNight().getAgitation())
                                                .height(waveForecast.getNight().getHeight())
                                                .direction(waveForecast.getNight().getDirection())
                                                .wind(waveForecast.getNight().getWind())
                                                .windDirection(waveForecast.getNight().getWindDirection()).build()).build())
                .build();
    }

    private NotifyForecastResponseDTO notifyAsync(List<User> usersToBeNotified, UserNotificationDTO notificationDTO)
            throws DesafioBackendItArcApiException {
        final List<NotifyForecastResponseUserErrorDTO> usersWithNotificationError = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (User user : usersToBeNotified) {
            executorService.submit(() -> {
                try {
                    mockClientService.notifyUser(notificationDTO, user.getId());
                } catch (Exception e) {
                    usersWithNotificationError.add(
                            NotifyForecastResponseUserErrorDTO.builder().userId(user.getId()).errorMsg(e.getMessage())
                                    .build());
                    log.error("Error notifying user with ID {}: {}", user.getId(), e.getMessage());
                }
            });
        }

        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }

        if (usersWithNotificationError.size() == usersToBeNotified.size()) {
            log.error("No users were notified: {}", usersWithNotificationError);
            throw new DesafioBackendItArcApiException("No users were notified", usersWithNotificationError);
        }

        return NotifyForecastResponseDTO.builder().usersWithNotificationError(usersWithNotificationError).build();
    }
}
