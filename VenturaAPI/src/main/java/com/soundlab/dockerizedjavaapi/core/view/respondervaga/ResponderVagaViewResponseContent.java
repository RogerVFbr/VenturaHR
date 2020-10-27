package com.soundlab.dockerizedjavaapi.core.view.respondervaga;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaCriterio;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaType;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class ResponderVagaViewResponseContent {
    private Long id;
    private String ownerName;
    private String shortDescription;
    private String longDescription;
    private String city;
    private String state;
    private VagaType type;
    private String timeSpan;
    private LocalDateTime expirationDate;
    private List<VagaCriterio> vagaCriterios;
}
