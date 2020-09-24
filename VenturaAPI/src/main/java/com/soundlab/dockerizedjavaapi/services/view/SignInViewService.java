package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseContent;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseUser;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseVaga;
import com.soundlab.dockerizedjavaapi.exceptions.InvalidCredentialsException;
import com.soundlab.dockerizedjavaapi.services.domain.UserService;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;
import com.soundlab.dockerizedjavaapi.utils.StringResources;

import org.springframework.stereotype.Service;

@Service
public class SignInViewService implements IViewService<SignInViewResponseContent> {

    private final VagaService vagaService;
    private final UserService userService;

    public SignInViewService(VagaService vagaService, UserService userService) {
        this.vagaService = vagaService;
        this.userService = userService;
    }

    public SignInViewResponseContent getContent() {
        return new SignInViewResponseContent(
            StringResources.COMPANY_LOGO_TEXT,
            StringResources.PAGE_FOOTER_TEXT,
            StringResources.MEMBER_BENEFITS,
            vagaService.listLatestAvailable(SignInViewResponseVaga.class)
        );
    }

    public SignInViewResponseUser requestSignIn(String email, String password) {
        SignInViewResponseUser user = userService.findByEmail(email, SignInViewResponseUser.class);
        if (user == null || !password.equals(user.getPassword()))
            throw new InvalidCredentialsException();
        return user;
    }
}
