package com.invext.app.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("Erro ao ler a requisição: " + e.getMessage());
        return ResponseEntity.badRequest().body(
                ErrorResponse.builder()
                        .exceptionMessage(e.getMessage())
                        .message("Erro ao ler a requisição.")
                        .messageCode("99")
                        .build()
        );
    }

}
