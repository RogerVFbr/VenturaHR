package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.core.view.publishvaga.PublishVagaViewRequestVaga;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PublishVagaViewService {

    VagaService vagaService;
    ModelMapper modelMapper;

    public PublishVagaViewService(VagaService vagaService, ModelMapper modelMapper) {
        this.vagaService = vagaService;
        this.modelMapper = modelMapper;
    }

    public Vaga publishVaga(PublishVagaViewRequestVaga vaga) {
        return vagaService.save(modelMapper.map(vaga, Vaga.class));
    }
}
