package com.soundlab.dockerizedjavaapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class SceneNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SceneNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String sceneNotFoundHandler(SceneNotFoundException ex) {
        return ex.getMessage();
    }
}
