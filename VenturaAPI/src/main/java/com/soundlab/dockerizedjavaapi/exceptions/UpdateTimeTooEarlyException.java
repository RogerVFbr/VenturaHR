package com.soundlab.dockerizedjavaapi.exceptions;

public class UpdateTimeTooEarlyException extends RuntimeException {
    public UpdateTimeTooEarlyException(Long id) {
        super("Scene '" + id + "' update time must be later than last date modified.");
    }
}
