package com.soundlab.dockerizedjavaapi.exceptions;

import com.soundlab.dockerizedjavaapi.models.Enums;

public class InvalidStatusUpdateException extends RuntimeException {

    public InvalidStatusUpdateException(Long id, Enums.SceneStatus sceneStatus,
                                        Enums.SceneStatus newStatus) {
        super("Scene '" + id + "' status is '" + sceneStatus.toString() + "' and cannot be " +
            "updated to '" + newStatus.toString() + "'.");
    }
}
