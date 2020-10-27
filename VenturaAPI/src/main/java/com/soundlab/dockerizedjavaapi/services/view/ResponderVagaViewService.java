package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.domain.resposta.Resposta;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.core.view.respondervaga.ResponderVagaViewRequestResposta;
import com.soundlab.dockerizedjavaapi.core.view.respondervaga.ResponderVagaViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.domain.RespostaService;
import com.soundlab.dockerizedjavaapi.services.domain.UserService;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class ResponderVagaViewService {

    RespostaService respostaService;
    VagaService vagaService;
    UserService userService;
    ModelMapper modelMapper;

    public ResponderVagaViewService(RespostaService respostaService,
                                    VagaService vagaService,
                                    UserService userService,
                                    ModelMapper modelMapper) {
        this.respostaService = respostaService;
        this.vagaService = vagaService;
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    public ResponderVagaViewResponseContent getContent(Long vagaId) {
        Vaga vagaFull = vagaService.findById(vagaId);
        String ownerName = userService.findById(vagaFull.getOwnerId()).getName();
        ResponderVagaViewResponseContent vaga = modelMapper.map(vagaFull,
            ResponderVagaViewResponseContent.class);
        vaga.setOwnerName(ownerName);
        return vaga;
    }

    public Resposta requestAnswer(ResponderVagaViewRequestResposta resposta) {
        return respostaService.save(modelMapper.map(resposta, Resposta.class));
    }
}
