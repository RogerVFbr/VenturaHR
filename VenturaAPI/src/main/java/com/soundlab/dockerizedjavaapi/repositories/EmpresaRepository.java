package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.models.Empresa;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
