package com.soundlab.dockerizedjavaapi.exceptions;

public class InvalidDateTimeFormatException extends RuntimeException {

    public InvalidDateTimeFormatException(String sceneUpdateTime) {
        super("Scene update time string '" + sceneUpdateTime + "' has an invalid format. Please use 'yyyy-MM-ddTHH:mm'.");
    }
}
