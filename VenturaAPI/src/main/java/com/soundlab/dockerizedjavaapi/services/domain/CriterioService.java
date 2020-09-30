package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaCriterio;
import com.soundlab.dockerizedjavaapi.repositories.CriterioRepository;

import org.springframework.stereotype.Service;

@Service
public class CriterioService extends GenericService<CriterioRepository, VagaCriterio> {
    public CriterioService(CriterioRepository criterioRepository) {
        super(criterioRepository);
    }
}
