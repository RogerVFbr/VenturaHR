package com.soundlab.dockerizedjavaapi.core.view.respondervaga;

import com.soundlab.dockerizedjavaapi.core.domain.resposta.RespostaCriterio;

import java.util.List;

import lombok.Data;

@Data
public class ResponderVagaViewRequestResposta {
    private Long vagaId;
    private Long candidatoId;
    private String curriculoUrl;
    private String textContent;
    private List<RespostaCriterio> respostasCriterios;
}
