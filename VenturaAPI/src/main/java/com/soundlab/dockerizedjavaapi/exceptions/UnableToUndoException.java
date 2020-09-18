package com.soundlab.dockerizedjavaapi.exceptions;

import com.soundlab.dockerizedjavaapi.models.Enums;

public class UnableToUndoException extends RuntimeException {

    public UnableToUndoException(Long id, Enums.SceneStatus sceneStatus) {
        super("Scene '" + id + "' status is '" + sceneStatus.toString() + "' and cannot be " +
            "undone.");
    }
}
