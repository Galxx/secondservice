package com.example.secondservice.controller;

import com.example.secondservice.domain.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.RestClientException;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerExceptionHandler {
    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(RestClientException ex, WebRequest request) {
        System.out.println(ex.getMessage());
        ErrorMessage message = new ErrorMessage("Ошибка при получени списка пользователей");

        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
