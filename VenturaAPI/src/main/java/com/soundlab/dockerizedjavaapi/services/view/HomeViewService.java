package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.view.ViewResponseVagaLight;
import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewRequestSearch;
import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;
import com.soundlab.dockerizedjavaapi.services.utils.StringResourcesService;

import java.util.List;

public abstract class HomeViewService {

    VagaService vagaService;
    StringResourcesService stringResourcesService;

    protected HomeViewService(VagaService vagaService,
                              StringResourcesService stringResourcesService) {
        this.vagaService = vagaService;
        this.stringResourcesService = stringResourcesService;
    }

    public HomeViewResponseContent getContent(Long userId) {
        List<ViewResponseVagaLight> vagasSearch = vagaService.listLatestAvailable(ViewResponseVagaLight.class);
        return buildContent(userId, vagasSearch);
    }

    public HomeViewResponseContent getContent(Long userId, HomeViewRequestSearch search) {
        if (search.getContent().isEmpty()) return getContent(userId);
        List<ViewResponseVagaLight> vagaSearch = vagaService.listByInclusionType(search.getContent(), search.getType(), ViewResponseVagaLight.class);
        return buildContent(userId, vagaSearch);
    }

    protected abstract HomeViewResponseContent buildContent(Long userId, List<ViewResponseVagaLight> vagas);
}
