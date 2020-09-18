package com.soundlab.dockerizedjavaapi.controllers.validation;

import com.soundlab.dockerizedjavaapi.exceptions.*;
import com.soundlab.dockerizedjavaapi.models.Enums;

import java.sql.Timestamp;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

public class SceneControllerValidation {

    private static final long UNDO_TIMEOUT = 300L;

    public static void validateStatusUpdate(Long sceneId, Enums.SceneStatus current,
                                            Enums.SceneStatus updated) {

        List<Boolean> conditions = Arrays.asList(
            current.equals(updated),
            current.equals(Enums.SceneStatus.PENDENTE) && updated.equals(Enums.SceneStatus.GRAVADA),
            current.equals(Enums.SceneStatus.PENDENTE) && updated.equals(Enums.SceneStatus.PENDURADA),
            current.equals(Enums.SceneStatus.PREPARADA) && updated.equals(Enums.SceneStatus.PENDENTE),
            current.equals(Enums.SceneStatus.GRAVADA) || current.equals(Enums.SceneStatus.PENDURADA)
        );

        if (!conditions.contains(true)) return;
        throw new InvalidStatusUpdateException(sceneId, current, updated);
    }

    public static void validateUndoStatusUpdate(Long sceneId, Enums.SceneStatus currentStatus,
                                                             Timestamp dateModified) {

        LocalDateTime sceneTime = dateModified.toLocalDateTime();
        LocalDateTime currentTime = LocalDateTime.now();
        long durationInSeconds = Duration.between (sceneTime, currentTime).getSeconds();

        if (durationInSeconds > UNDO_TIMEOUT)
            throw new UndoTimeOutException(sceneId, currentStatus);
    }

    public static void validateUpdateTime(Long sceneId, String sceneUpdateTime, Timestamp lastUpdate) {

        LocalDateTime sceneTime = parseDateTimeFormat(sceneUpdateTime);

        if(sceneTime == null)
            throw new InvalidDateTimeFormatException(sceneUpdateTime);

        LocalDateTime lastTime = lastUpdate.toLocalDateTime();
        LocalDateTime currentTime = LocalDateTime.now();

        if (sceneTime.isBefore(lastTime)) {
            throw new UpdateTimeTooEarlyException(sceneId);
        }

        if (sceneTime.isAfter(currentTime)) {
            throw  new UpdateTimeTooLateException(sceneId);
        }
    }

    public static LocalDateTime parseDateTimeFormat(String sceneUpdateTime) {
        LocalDateTime sceneTime = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
            sceneTime = LocalDateTime.parse(sceneUpdateTime, formatter);
            System.out.println(sceneTime.toString());
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sceneTime;
    }
}
