package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.view.ranking.RankingViewResponseContent;
import com.soundlab.dockerizedjavaapi.core.view.ranking.RankingViewResponseResposta;
import com.soundlab.dockerizedjavaapi.services.view.RankingViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
    name = "View: Ranking",
    description = "Gerencia informações de view."
)
public class RankingViewController {

    RankingViewService rankingViewService;

    public RankingViewController(RankingViewService rankingViewService) {
        this.rankingViewService = rankingViewService;
    }

    @GetMapping("/ranking/{id}")
    public ResponseEntity<RankingViewResponseContent> getContent(@PathVariable long id) {
        return ResponseEntity.ok(rankingViewService.getContent(id));
    }

    @GetMapping("/ranking/resposta/{id}")
    public ResponseEntity<RankingViewResponseResposta> getResposta(@PathVariable long id) {
        return ResponseEntity.ok(rankingViewService.getResposta(id));
    }
}
