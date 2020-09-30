package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.view.ViewResponseVagaLight;
import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewResponseContent;
import com.soundlab.dockerizedjavaapi.core.view.home.HomeViewResponseVaga;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;
import com.soundlab.dockerizedjavaapi.services.utils.StringResourcesService;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeEmpresaViewService extends HomeViewService {

    public HomeEmpresaViewService(VagaService vagaService,
                                  StringResourcesService stringResourcesService) {
        super(vagaService, stringResourcesService);
    }

    protected HomeViewResponseContent buildContent(Long empresaId, List<ViewResponseVagaLight> vagas) {
        return new HomeViewResponseContent(
            stringResourcesService.getSystemMessages(),
            vagaService.listByOwner(empresaId, HomeViewResponseVaga.class),
            vagas
        );
    }
}
