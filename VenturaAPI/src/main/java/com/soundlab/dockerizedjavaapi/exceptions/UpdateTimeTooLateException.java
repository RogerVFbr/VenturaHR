package com.soundlab.dockerizedjavaapi.exceptions;

public class UpdateTimeTooLateException extends RuntimeException {
    public UpdateTimeTooLateException(Long id) {
        super("Scene '" + id + "' update time must be earlier than current time.");
    }
}
