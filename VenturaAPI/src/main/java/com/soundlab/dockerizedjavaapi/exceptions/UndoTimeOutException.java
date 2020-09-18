package com.soundlab.dockerizedjavaapi.exceptions;

import com.soundlab.dockerizedjavaapi.models.Enums;

public class UndoTimeOutException extends RuntimeException {
    public UndoTimeOutException(Long id, Enums.SceneStatus sceneStatus) {
        super("Scene '" + id + "' last status update is too old to be undone.");
    }
}
