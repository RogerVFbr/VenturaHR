package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaCriterio;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CriterioRepository extends JpaRepository<VagaCriterio, Long> {
}
