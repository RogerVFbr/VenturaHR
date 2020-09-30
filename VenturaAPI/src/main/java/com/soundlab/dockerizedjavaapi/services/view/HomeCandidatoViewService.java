package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.view.ViewResponseVagaLight;
import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewResponseContent;
import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewResponseVaga;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;
import com.soundlab.dockerizedjavaapi.services.utils.StringResourcesService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeCandidatoViewService extends HomeViewService{

    public HomeCandidatoViewService(VagaService vagaService,
                                    StringResourcesService stringResourcesService) {
        super(vagaService, stringResourcesService);
    }

    protected HomeViewResponseContent buildContent(Long candidatoId, List<ViewResponseVagaLight> vagas) {
        return new HomeViewResponseContent(
            stringResourcesService.getSystemMessages(),
            vagaService.listByCandidateAnswers(candidatoId, HomeViewResponseVaga.class),
            vagas
        );
    }
}
