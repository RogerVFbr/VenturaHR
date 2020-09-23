package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Criterio;
import com.soundlab.dockerizedjavaapi.repositories.CriterioRepository;

import org.springframework.stereotype.Service;

@Service
public class CriterioService extends GenericService<CriterioRepository, Criterio> {
    public CriterioService(CriterioRepository criterioRepository) {
        super(criterioRepository);
    }
}
