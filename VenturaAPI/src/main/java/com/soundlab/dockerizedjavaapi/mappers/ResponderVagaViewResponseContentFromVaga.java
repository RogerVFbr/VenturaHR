package com.soundlab.dockerizedjavaapi.mappers;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;
import com.soundlab.dockerizedjavaapi.core.view.respondervaga.ResponderVagaViewResponseContent;

import org.modelmapper.PropertyMap;

public class ResponderVagaViewResponseContentFromVaga extends PropertyMap<Vaga, ResponderVagaViewResponseContent> {
    @Override
    protected void configure() {
        map().setId(source.getId());
        map().setShortDescription(source.getShortDescription());
        map().setLongDescription(source.getLongDescription());
        map().setCity(source.getCity());
        map().setState(source.getState());
        map().setType(source.getType());
        map().setTimeSpan(source.getTimeSpan());
        map().setExpirationDate(source.getExpirationDate());
        map().setVagaCriterios(source.getVagaCriterios());
    }
}
