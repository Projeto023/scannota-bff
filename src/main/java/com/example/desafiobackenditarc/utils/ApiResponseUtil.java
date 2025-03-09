package com.example.desafiobackenditarc.utils;

import com.example.desafiobackenditarc.dto.generic.ApiResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ApiResponseUtil {
    public static <T> ResponseEntity<ApiResponseDTO<T>> buildSuccessResponse(T data) {
        ApiResponseDTO<T> response = new ApiResponseDTO<>(true, "success", data);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponseDTO<T>> buildErrorResponse(String message,
                                                                           HttpStatus status,
                                                                           T data) {
        ApiResponseDTO<T> response = new ApiResponseDTO<>(false, message, data);
        return new ResponseEntity<>(response, status);
    }
}
