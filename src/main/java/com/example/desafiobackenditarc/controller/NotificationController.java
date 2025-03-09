package com.example.desafiobackenditarc.controller;

import com.example.desafiobackenditarc.dto.request.NotifyForecastRequestDTO;
import com.example.desafiobackenditarc.dto.request.ScheduleForecastRequestDTO;
import com.example.desafiobackenditarc.dto.response.NotifyForecastResponseDTO;
import com.example.desafiobackenditarc.exception.CPTECException;
import com.example.desafiobackenditarc.exception.DesafioBackendItArcApiException;
import com.example.desafiobackenditarc.exception.EntityNotFoundException;
import com.example.desafiobackenditarc.service.NotifyForecastScheduledService;
import com.example.desafiobackenditarc.service.NotifyForecastService;
import com.example.desafiobackenditarc.service.ScheduleForecastService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static com.example.desafiobackenditarc.utils.ApiResponseUtil.buildSuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/notify")
public class NotificationController {

    private final NotifyForecastService notifyForecastService;
    private final NotifyForecastScheduledService notifyForecastScheduledService;
    private final ScheduleForecastService scheduleForecastService;

    @PostMapping("/forecast")
    @Operation(description = "Notify users from a city")
    public ResponseEntity notifyForecastDirect(@Valid @RequestBody final NotifyForecastRequestDTO request)
            throws DesafioBackendItArcApiException, EntityNotFoundException, CPTECException {
        final NotifyForecastResponseDTO response = notifyForecastService.process(request);
        return buildSuccessResponse(response);
    }

    @PostMapping("/forecast/scheduled/{notificationId}")
    @Operation(description = "Notify city that was already scheduled")
    public ResponseEntity notifyForecastScheduled(@PathVariable("notificationId") final Integer notificationId)
            throws DesafioBackendItArcApiException, EntityNotFoundException, CPTECException {
        final NotifyForecastResponseDTO response = notifyForecastScheduledService.process(notificationId);
        return buildSuccessResponse(response);
    }

    @PostMapping("/schedule")
    @Operation(description = "Schedule a notification for a city")
    public ResponseEntity scheduleForecast(@Valid @RequestBody final ScheduleForecastRequestDTO request) {
        scheduleForecastService.process(request);
        return buildSuccessResponse("Notification for city " + request.getCityName() + " successfully scheduled");
    }
}
