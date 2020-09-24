package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.repositories.VagaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VagaService extends GenericService<VagaRepository, Vaga> {
    public VagaService(VagaRepository vagaRepository) {
        super(vagaRepository);
    }

    public <T> List<T> listLatestAvailable(Class<T> type) {
        return repository.findTop10ByExpirationDateIsAfterOrderByDateCreatedDesc(
            LocalDateTime.now(), type);
    }
}
