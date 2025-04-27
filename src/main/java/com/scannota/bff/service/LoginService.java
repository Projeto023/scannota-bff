package com.scannota.bff.service;



import com.scannota.bff.dto.request.LoginRequestDTO;
import com.scannota.bff.dto.response.LoginResponseDTO;

import java.text.ParseException;

public interface LoginService {
    LoginResponseDTO process(LoginRequestDTO loginRequestDTO) throws ParseException;
}
