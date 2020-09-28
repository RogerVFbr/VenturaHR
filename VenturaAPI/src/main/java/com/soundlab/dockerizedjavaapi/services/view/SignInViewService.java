package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseContent;
import com.soundlab.dockerizedjavaapi.core.view.ViewResponseUser;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseVaga;
import com.soundlab.dockerizedjavaapi.exceptions.InvalidCredentialsException;
import com.soundlab.dockerizedjavaapi.services.domain.UserService;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;
import com.soundlab.dockerizedjavaapi.services.utils.StringResourcesService;

import org.springframework.stereotype.Service;

@Service
public class SignInViewService extends ViewService<SignInViewResponseContent> {

    private final VagaService vagaService;
    private final UserService userService;

    public SignInViewService(VagaService vagaService,
                             UserService userService,
                             StringResourcesService stringResourcesService) {
        super(stringResourcesService);
        this.vagaService = vagaService;
        this.userService = userService;
    }

    public SignInViewResponseContent getContent() {
        return new SignInViewResponseContent(
            stringResourcesService.getCompanyLogoText(),
            stringResourcesService.getPageFooterText(),
            stringResourcesService.getMemberBenefits(),
            vagaService.listLatestAvailable(SignInViewResponseVaga.class)
        );
    }

    public ViewResponseUser requestSignIn(String email, String password) {
        ViewResponseUser user = userService.findByEmail(email, ViewResponseUser.class);
        if (user == null || !password.equals(user.getPassword()))
            throw new InvalidCredentialsException();
        return user;
    }
}
