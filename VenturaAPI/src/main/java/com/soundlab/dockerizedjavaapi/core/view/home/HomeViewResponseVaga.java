package com.soundlab.dockerizedjavaapi.core.view.home;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface HomeViewResponseVaga {
    String getId();
    String getShortDescription();

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    LocalDateTime getExpirationDate();
}
