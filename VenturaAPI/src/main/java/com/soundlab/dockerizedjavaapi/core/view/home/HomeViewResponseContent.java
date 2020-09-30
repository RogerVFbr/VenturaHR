package com.soundlab.dockerizedjavaapi.core.view.home;

import com.soundlab.dockerizedjavaapi.core.view.ViewResponseVagaLight;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class HomeViewResponseContent {
    private String messages;
    private List<HomeViewResponseVaga> vagasRespondidas;
    private List<ViewResponseVagaLight> vagasBusca;
}
