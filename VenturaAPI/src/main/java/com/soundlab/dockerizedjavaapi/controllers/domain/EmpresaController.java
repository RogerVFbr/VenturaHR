package com.soundlab.dockerizedjavaapi.controllers.domain;

import com.soundlab.dockerizedjavaapi.core.domain.user.Empresa;
import com.soundlab.dockerizedjavaapi.services.domain.EmpresaService;

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
    name = "Domain: Users - Empresas",
    description = "Gerencia usuários do tipo 'Empresa' e projeções."
)
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
