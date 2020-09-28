package com.soundlab.dockerizedjavaapi.exceptions;

public class InvalidRegistrationException extends RuntimeException{
    public InvalidRegistrationException() {
        super("User already exists.");
    }
}
