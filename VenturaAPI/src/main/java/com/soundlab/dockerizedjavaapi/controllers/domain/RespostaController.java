package com.soundlab.dockerizedjavaapi.controllers.domain;

import com.soundlab.dockerizedjavaapi.core.domain.resposta.Resposta;
import com.soundlab.dockerizedjavaapi.services.domain.RespostaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RespostaController {
    private final RespostaService respostaService;

    public RespostaController(RespostaService respostaService) {
        this.respostaService = respostaService;
    }

    @GetMapping("/respostas")
    List<Resposta> findAll() {
        return respostaService.findAll();
    }

    @GetMapping("/respostas/{id}")
    Resposta getById(@PathVariable long id) {
        return respostaService.findById(id);
    }

    @PostMapping("/respostas")
    Resposta save(@RequestBody Resposta resposta) {
        return respostaService.save(resposta);
    }

    @PutMapping("/respostas/{id}")
    Resposta update(@RequestBody Resposta resposta, @PathVariable long id) {
        return respostaService.update(resposta);
    }

    @DeleteMapping("/respostas/{id}")
    void delete(@PathVariable long id) {
        respostaService.delete(id);
    }
}
