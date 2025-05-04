package com.scannota.bff.utils;

import com.scannota.bff.dto.response.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {

    public static ResponseEntity buildSuccessResponse(final Object data) {
        return ResponseEntity.status(HttpStatus.OK).body(
                ApiResponseDTO.builder()
                        .success(true)
                        .message("success")
                        .data(data)
                        .build());
    }

    public static ResponseEntity buildErrorResponse(final String message, final HttpStatus status) {
        return ResponseEntity.status(status).body(
                ApiResponseDTO.builder()
                        .success(false)
                        .message(message)
                        .build());
    }
}
