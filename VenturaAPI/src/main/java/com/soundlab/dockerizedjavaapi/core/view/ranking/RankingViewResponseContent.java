package com.soundlab.dockerizedjavaapi.core.view.ranking;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaType;

import java.time.LocalDateTime;
import java.util.List;

public interface RankingViewResponseContent {
    String getShortDescription();
    String getCity();
    String getState();
    VagaType getType();

    @JsonFormat(pattern="dd/MM/yy")
    LocalDateTime getExpirationDate();
    Double getPerfil();
    List<RankingViewResponseResposta> getRespostas();
}
