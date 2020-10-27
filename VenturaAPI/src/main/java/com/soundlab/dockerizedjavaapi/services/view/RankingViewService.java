package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.view.ranking.RankingViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;

import org.springframework.stereotype.Service;

@Service
public class RankingViewService {

    VagaService vagaService;

    public RankingViewService(VagaService vagaService) {
        this.vagaService = vagaService;
    }

    public RankingViewResponseContent getContent(Long vagaId) {
        return vagaService.findById(vagaId, RankingViewResponseContent.class);
    }
}
