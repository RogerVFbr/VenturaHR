package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface VagaRepository extends JpaRepository<Vaga, Long> {
    <T> List<T> findTop10ByExpirationDateIsAfterOrderByDateCreatedDesc(LocalDateTime expirationDate, Class<T> type);
}
