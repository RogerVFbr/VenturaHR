package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.core.view.publishvaga.PublishVagaViewRequestVaga;
import com.soundlab.dockerizedjavaapi.services.view.PublishVagaViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublishVagaViewContoller {

    PublishVagaViewService publishVagaViewService;

    public PublishVagaViewContoller(PublishVagaViewService publishVagaViewService) {
        this.publishVagaViewService = publishVagaViewService;
    }

    @PostMapping("/publish-vaga")
    public ResponseEntity<Vaga> requestSignUp(@RequestBody PublishVagaViewRequestVaga vaga) {
        return ResponseEntity.ok(publishVagaViewService.publishVaga(vaga));
    }
}
