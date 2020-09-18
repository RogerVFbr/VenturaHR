package com.soundlab.dockerizedjavaapi.exceptions;

public class SceneNotFoundException extends RuntimeException{
    public SceneNotFoundException(long id) {
        super("Could not find Scene " + id);
    }
}
