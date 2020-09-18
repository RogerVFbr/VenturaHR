package com.soundlab.dockerizedjavaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UpdateTimeTooLateAdvice {

    @ResponseBody
    @ExceptionHandler(UpdateTimeTooLateException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String updateTimeTooLateHandler(UpdateTimeTooLateException ex) {
        return ex.getMessage();
    }
}
