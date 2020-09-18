package com.soundlab.dockerizedjavaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UndoTimeOutAdvice {

    @ResponseBody
    @ExceptionHandler(UndoTimeOutException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String unableToUndoHandler(UndoTimeOutException ex) {
        return ex.getMessage();
    }
}
