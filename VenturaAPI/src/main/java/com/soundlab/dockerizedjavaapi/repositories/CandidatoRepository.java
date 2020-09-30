package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.domain.user.Candidato;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CandidatoRepository extends JpaRepository<Candidato, Long> {
}
