package com.soundlab.dockerizedjavaapi.services;

import com.soundlab.dockerizedjavaapi.core.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.repositories.VagaRepository;

import org.springframework.stereotype.Service;

@Service
public class VagaService extends GenericService<VagaRepository, Vaga>  {

    public VagaService(VagaRepository vagaRepository) {
        super(vagaRepository);
    }
}
