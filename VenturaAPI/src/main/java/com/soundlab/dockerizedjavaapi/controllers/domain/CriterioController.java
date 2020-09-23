package com.soundlab.dockerizedjavaapi.controllers.domain;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Criterio;
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
    List<Criterio> findAll() {
        return criterioService.findAll();
    }

    @GetMapping("/criterios/{id}")
    Criterio getById(@PathVariable long id) {
        return criterioService.findById(id);
    }

    @PostMapping("/criterios")
    Criterio save(@RequestBody Criterio criterio) {
        return criterioService.save(criterio);
    }

    @PutMapping("/criterios/{id}")
    Criterio update(@RequestBody Criterio criterio, @PathVariable long id) {
        return criterioService.update(criterio);
    }

    @DeleteMapping("/criterios/{id}")
    void delete(@PathVariable long id) {
        criterioService.delete(id);
    }
}
