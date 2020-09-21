package com.soundlab.dockerizedjavaapi.controllers.domain;

import com.soundlab.dockerizedjavaapi.core.domain.user.Candidato;
import com.soundlab.dockerizedjavaapi.services.domain.CandidatoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CandidatoController {
    private final CandidatoService candidatoService;

    public CandidatoController(CandidatoService candidatoService){
        this.candidatoService = candidatoService;
    }

    @GetMapping("/users/candidatos")
    List<Candidato> findAll() {
       return candidatoService.findAll();
    }

    @GetMapping("/users/candidatos/{id}")
    Candidato getById(@PathVariable long id) {
        return candidatoService.findById(id);
    }

    @PostMapping("/users/candidatos")
    Candidato save(@RequestBody Candidato candidato) {
        return candidatoService.save(candidato);
    }

    @PutMapping("/users/candidatos/{id}")
    Candidato update(@RequestBody Candidato candidato, @PathVariable long id) {
        return candidatoService.update(candidato);
    }

    @DeleteMapping("/users/candidatos/{id}")
    void delete(@PathVariable long id) {
        candidatoService.delete(id);
    }
}
