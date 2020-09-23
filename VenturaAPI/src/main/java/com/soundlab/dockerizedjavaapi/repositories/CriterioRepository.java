package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Criterio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CriterioRepository extends JpaRepository<Criterio, Long> {
}
