package com.soundlab.dockerizedjavaapi.core.view.ranking;

import java.util.List;

public interface RankingViewResponseResposta {
    RankingViewResponseCandidato getCandidato();
    String getCurriculoUrl();
    String getTextContent();
    List<RankingViewResponseRespostaCriterio> getRespostasCriterios();
    Double getPerfilResposta();
}
