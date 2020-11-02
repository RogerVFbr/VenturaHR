package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.view.ranking.RankingViewResponseContent;
import com.soundlab.dockerizedjavaapi.core.view.ranking.RankingViewResponseResposta;
import com.soundlab.dockerizedjavaapi.services.domain.RespostaService;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;

import org.springframework.stereotype.Service;

@Service
public class RankingViewService {

    VagaService vagaService;
    RespostaService respostaService;

    public RankingViewService(VagaService vagaService, RespostaService respostaService) {
        this.vagaService = vagaService;
        this.respostaService = respostaService;
    }

    public RankingViewResponseContent getContent(Long vagaId) {
        return vagaService.findById(vagaId, RankingViewResponseContent.class);
    }

    public RankingViewResponseResposta getResposta(Long vagaId) {
        return respostaService.findById(vagaId, RankingViewResponseResposta.class);
    }
}
