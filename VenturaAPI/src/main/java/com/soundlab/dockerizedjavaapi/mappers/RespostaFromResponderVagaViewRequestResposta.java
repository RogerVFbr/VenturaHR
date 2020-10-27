package com.soundlab.dockerizedjavaapi.mappers;

import com.soundlab.dockerizedjavaapi.core.domain.resposta.Resposta;
import com.soundlab.dockerizedjavaapi.core.view.respondervaga.ResponderVagaViewRequestResposta;

import org.modelmapper.PropertyMap;

public class RespostaFromResponderVagaViewRequestResposta extends PropertyMap<ResponderVagaViewRequestResposta, Resposta> {
    @Override
    protected void configure() {
        map().setId(null);
        map().setVagaId(source.getVagaId());
        map().setCandidatoId(source.getCandidatoId());
        map().setCurriculoUrl(source.getCurriculoUrl());
        map().setRespostasCriterios(source.getRespostasCriterios());
        map().setTextContent(source.getTextContent());
    }
}
