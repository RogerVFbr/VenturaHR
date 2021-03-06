package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.domain.resposta.Resposta;
import com.soundlab.dockerizedjavaapi.core.view.respondervaga.ResponderVagaViewRequestResposta;
import com.soundlab.dockerizedjavaapi.core.view.respondervaga.ResponderVagaViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.view.ResponderVagaViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
    name = "View: Responder Vaga",
    description = "Gerencia informações de view."
)
public class ResponderVagaViewController {

    ResponderVagaViewService responderVagaViewService;

    public ResponderVagaViewController(ResponderVagaViewService responderVagaViewService) {
        this.responderVagaViewService = responderVagaViewService;
    }

    @GetMapping("/responder-vaga/{id}")
    public ResponseEntity<ResponderVagaViewResponseContent> getContent(@PathVariable long id) {
        return ResponseEntity.ok(responderVagaViewService.getContent(id));
    }

    @PostMapping("/responder-vaga")
    public ResponseEntity<Resposta> requestAnswer(@RequestBody ResponderVagaViewRequestResposta resposta) {
        return ResponseEntity.ok(responderVagaViewService.requestAnswer(resposta));
    }
}
