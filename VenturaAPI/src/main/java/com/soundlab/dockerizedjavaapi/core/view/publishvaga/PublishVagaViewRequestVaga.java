package com.soundlab.dockerizedjavaapi.core.view.publishvaga;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaCriterio;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaType;

import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;

@Data
public class PublishVagaViewRequestVaga {
    private Long ownerId;
    private String shortDescription;
    private String longDescription;
    private String city;
    private String state;
    private VagaType type;
    private String timeSpan;
    private LocalDateTime expirationDate;
    private List<VagaCriterio> vagaCriterios;
}
