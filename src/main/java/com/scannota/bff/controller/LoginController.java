package com.scannota.bff.controller;



import com.scannota.bff.dto.request.LoginRequestDTO;
import com.scannota.bff.dto.response.LoginResponseDTO;
import com.scannota.bff.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

import static com.scannota.bff.utils.ApiResponseUtil.buildSuccessResponse;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/login")
public class LoginController {
    private final LoginService loginService;

    @PostMapping()
    public ResponseEntity login(
            @RequestBody final LoginRequestDTO loginRequestDTO
    ) throws ParseException {
        final LoginResponseDTO user = loginService.process(loginRequestDTO);
        return buildSuccessResponse(user);
    }
}
