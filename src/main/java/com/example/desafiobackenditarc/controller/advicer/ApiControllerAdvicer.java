package com.example.desafiobackenditarc.controller.advicer;

import com.example.desafiobackenditarc.exception.DesafioBackendItArcApiException;
import com.example.desafiobackenditarc.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.example.desafiobackenditarc.utils.ApiResponseUtil.buildErrorResponse;

@Slf4j
@ControllerAdvice
public class ApiControllerAdvicer {
    @ExceptionHandler({EntityNotFoundException.class})
    public ResponseEntity handleEntityNotFoundException(final EntityNotFoundException ex) {
        log.error(ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND, null);
    }

    @ExceptionHandler({DesafioBackendItArcApiException.class})
    public ResponseEntity handleDesafioBackendItArcApiException(final DesafioBackendItArcApiException ex) {
        log.error(ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getAdditionalInfo());
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleGenericException(final Exception ex) {
        log.error(ex.getMessage());
        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, null);
    }
}
