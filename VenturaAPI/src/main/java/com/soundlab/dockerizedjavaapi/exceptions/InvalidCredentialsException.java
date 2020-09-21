package com.soundlab.dockerizedjavaapi.exceptions;

public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException() {
        super("Invalid credentials.");
    }
}
