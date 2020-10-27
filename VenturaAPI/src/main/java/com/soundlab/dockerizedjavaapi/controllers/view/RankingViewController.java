package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.view.ranking.RankingViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.view.RankingViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RankingViewController {

    RankingViewService rankingViewService;

    public RankingViewController(RankingViewService rankingViewService) {
        this.rankingViewService = rankingViewService;
    }

    @GetMapping("/ranking/{id}")
    public ResponseEntity<RankingViewResponseContent> getContent(@PathVariable long id) {
        return ResponseEntity.ok(rankingViewService.getContent(id));
    }
}
