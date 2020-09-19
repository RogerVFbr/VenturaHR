package com.soundlab.dockerizedjavaapi.services;

import com.soundlab.dockerizedjavaapi.core.user.Candidato;
import com.soundlab.dockerizedjavaapi.repositories.CandidatoRepository;

import org.springframework.stereotype.Service;

@Service
public class CandidatoService extends GenericService<CandidatoRepository, Candidato> {

    public CandidatoService(CandidatoRepository candidatoRepository) {
        super(candidatoRepository);
    }
}
