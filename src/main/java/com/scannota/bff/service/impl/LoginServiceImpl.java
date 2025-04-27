package com.scannota.bff.service.impl;


import com.scannota.bff.dto.request.LoginRequestDTO;
import com.scannota.bff.dto.response.LoginResponseDTO;
import com.scannota.bff.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    @Override
    public LoginResponseDTO process(LoginRequestDTO loginRequestDTO) throws ParseException {


        return LoginResponseDTO.builder()
                .build();

    }
}
