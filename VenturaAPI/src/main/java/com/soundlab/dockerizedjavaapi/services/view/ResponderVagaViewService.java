package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.domain.resposta.Resposta;
import com.soundlab.dockerizedjavaapi.core.view.respondervaga.ResponderVagaViewRequestResposta;
import com.soundlab.dockerizedjavaapi.core.view.respondervaga.ResponderVagaViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.domain.RespostaService;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ResponderVagaViewService {

    RespostaService respostaService;
    VagaService vagaService;
    ModelMapper modelMapper;

    public ResponderVagaViewService(RespostaService respostaService,
                                    VagaService vagaService,
                                    ModelMapper modelMapper) {
        this.respostaService = respostaService;
        this.vagaService = vagaService;
        this.modelMapper = modelMapper;
    }

    public ResponderVagaViewResponseContent getContent(Long vagaId) {
        return vagaService.findById(vagaId, ResponderVagaViewResponseContent.class);
    }

    public Resposta requestAnswer(ResponderVagaViewRequestResposta resposta) {
        return respostaService.save(modelMapper.map(resposta, Resposta.class));
    }
}
