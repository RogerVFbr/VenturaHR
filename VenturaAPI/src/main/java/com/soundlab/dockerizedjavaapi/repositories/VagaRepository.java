package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.vaga.Vaga;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
}
