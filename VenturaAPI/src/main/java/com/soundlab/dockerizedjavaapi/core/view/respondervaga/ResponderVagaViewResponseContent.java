package com.soundlab.dockerizedjavaapi.core.view.respondervaga;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaCriterio;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaType;

import java.time.LocalDateTime;
import java.util.List;

public interface ResponderVagaViewResponseContent {
    Long getId();
    ResponderVagaViewRequestEmpresa getOwner();
    String getShortDescription();
    String getLongDescription();
    String getCity();
    String getState();
    VagaType getType();
    String getTimeSpan();
    LocalDateTime getExpirationDate();
    List<VagaCriterio> getVagaCriterios();
}