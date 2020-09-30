package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseContent;
import com.soundlab.dockerizedjavaapi.core.view.ViewResponseUserLight;
import com.soundlab.dockerizedjavaapi.core.view.ViewResponseVagaLight;
import com.soundlab.dockerizedjavaapi.exceptions.InvalidCredentialsException;
import com.soundlab.dockerizedjavaapi.services.domain.UserService;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;
import com.soundlab.dockerizedjavaapi.services.utils.StringResourcesService;

import org.springframework.stereotype.Service;

@Service
public class SignInViewService {

    private final VagaService vagaService;
    private final UserService userService;
    private final StringResourcesService stringResourcesService;


    public SignInViewService(VagaService vagaService,
                             UserService userService,
                             StringResourcesService stringResourcesService) {
        this.vagaService = vagaService;
        this.userService = userService;
        this.stringResourcesService = stringResourcesService;
    }

    public SignInViewResponseContent getContent() {
        return new SignInViewResponseContent(
            stringResourcesService.getMemberBenefits(),
            vagaService.listLatestAvailable(ViewResponseVagaLight.class)
        );
    }

    public ViewResponseUserLight requestSignIn(String email, String password) {
        ViewResponseUserLight user = userService.findByEmail(email, ViewResponseUserLight.class);
        if (user == null || !password.equals(user.getPassword()))
            throw new InvalidCredentialsException();
        return user;
    }
}
