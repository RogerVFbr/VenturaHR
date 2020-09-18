package com.soundlab.dockerizedjavaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UnableToUndoAdvice {

    @ResponseBody
    @ExceptionHandler(UnableToUndoException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    String unableToUndoHandler(UnableToUndoException ex) {
        return ex.getMessage();
    }
}
