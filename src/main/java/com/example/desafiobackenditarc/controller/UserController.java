package com.example.desafiobackenditarc.controller;

import com.example.desafiobackenditarc.exception.EntityNotFoundException;
import com.example.desafiobackenditarc.service.UserPreferencesService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.desafiobackenditarc.utils.ApiResponseUtil.buildSuccessResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserPreferencesService userPreferencesService;

    @PatchMapping("/optout/{userId}")
    @Operation(description = "Opt out a user from receiving notifications")
    public ResponseEntity optOutUser(@PathVariable("userId") final Integer userId)
            throws EntityNotFoundException {
        userPreferencesService.changeUserAllowNotificationSetting(userId, false);
        return buildSuccessResponse("User id " + userId + " successfully opted out");
    }

    @PatchMapping("/optin/{userId}")
    @Operation(description = "Opt in a user to receive notifications")
    public ResponseEntity optInUser(@PathVariable("userId") final Integer userId)
            throws EntityNotFoundException {
        userPreferencesService.changeUserAllowNotificationSetting(userId, true);
        return buildSuccessResponse("User id " + userId + " successfully opted in");
    }
}
