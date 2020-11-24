package com.soundlab.dockerizedjavaapi.controllers.domain;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
    name = "Domain: Vagas",
    description = "Gerencia vagas publicadas."
)
public class VagaController {
    private final VagaService vagaService;

    public VagaController(VagaService vagaService){
        this.vagaService = vagaService;
    }

    @GetMapping("/vagas")
    List<Vaga> findAll() {
        return vagaService.findAll();
    }

    @GetMapping("/vagas/{id}")
    Vaga getById(@PathVariable long id) {
        return vagaService.findById(id);
    }

    @PostMapping("/vagas")
    Vaga save(@RequestBody Vaga vaga) {
        return vagaService.save(vaga);
    }

    @PutMapping("/vagas/{id}")
    Vaga update(@RequestBody Vaga vaga, @PathVariable long id) {
        return vagaService.update(vaga);
    }

    @DeleteMapping("/vagas/{id}")
    void delete(@PathVariable long id) {
        vagaService.delete(id);
    }

}
