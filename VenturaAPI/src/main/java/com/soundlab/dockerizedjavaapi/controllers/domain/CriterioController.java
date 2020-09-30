package com.soundlab.dockerizedjavaapi.controllers.domain;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaCriterio;
import com.soundlab.dockerizedjavaapi.services.domain.CriterioService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CriterioController {
    private final CriterioService criterioService;

    public CriterioController(CriterioService criterioService){
        this.criterioService = criterioService;
    }

    @GetMapping("/criterios")
    List<VagaCriterio> findAll() {
        return criterioService.findAll();
    }

    @GetMapping("/criterios/{id}")
    VagaCriterio getById(@PathVariable long id) {
        return criterioService.findById(id);
    }

    @PostMapping("/criterios")
    VagaCriterio save(@RequestBody VagaCriterio vagaCriterio) {
        return criterioService.save(vagaCriterio);
    }

    @PutMapping("/criterios/{id}")
    VagaCriterio update(@RequestBody VagaCriterio vagaCriterio, @PathVariable long id) {
        return criterioService.update(vagaCriterio);
    }

    @DeleteMapping("/criterios/{id}")
    void delete(@PathVariable long id) {
        criterioService.delete(id);
    }
}
