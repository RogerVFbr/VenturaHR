package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.resposta.Resposta;
import com.soundlab.dockerizedjavaapi.repositories.RespostaRepository;

import org.springframework.stereotype.Service;

@Service
public class RespostaService extends GenericService<RespostaRepository, Resposta>  {
    public RespostaService(RespostaRepository respostaRepository) {
        super(respostaRepository);
    }
}
