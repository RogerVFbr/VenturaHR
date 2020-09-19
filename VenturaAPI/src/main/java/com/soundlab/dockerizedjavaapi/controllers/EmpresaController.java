package com.soundlab.dockerizedjavaapi.controllers;

import com.soundlab.dockerizedjavaapi.models.Empresa;
import com.soundlab.dockerizedjavaapi.services.EmpresaService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmpresaController {
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService){
        this.empresaService = empresaService;
    }

    @GetMapping("/users/empresas")
    List<Empresa> findAll() {
        return empresaService.findAll();
    }

    @GetMapping("/users/empresas/{id}")
    Empresa getById(@PathVariable long id) {
        return empresaService.findById(id);
    }

    @PostMapping("/users/empresas")
    Empresa save(@RequestBody Empresa empresa) {
        return empresaService.save(empresa);
    }

    @PutMapping("/users/empresas/{id}")
    Empresa update(@RequestBody Empresa empresa, @PathVariable long id) {
        return empresaService.update(empresa);
    }

    @DeleteMapping("/users/empresas/{id}")
    void delete(@PathVariable long id) {
        empresaService.delete(id);
    }
}
