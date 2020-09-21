package com.soundlab.dockerizedjavaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class Advices {
    @ResponseBody
    @ExceptionHandler(InvalidCredentialsException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String invalidCredentialsExceptionHandler(InvalidCredentialsException ex) {
        return ex.getMessage();
    }
}
