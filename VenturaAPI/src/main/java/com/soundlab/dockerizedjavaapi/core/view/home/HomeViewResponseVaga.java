package com.soundlab.dockerizedjavaapi.core.view.home;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public interface HomeViewResponseVaga {
    String getId();
    String getShortDescription();
    String getLongDescription();
    String getCity();
    String getState();

    @JsonFormat(pattern="dd/MM/yy")
    LocalDateTime getExpirationDate();
}
