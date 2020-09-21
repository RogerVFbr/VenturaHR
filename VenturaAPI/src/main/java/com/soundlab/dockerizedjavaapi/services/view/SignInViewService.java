package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaStatus;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseContent;
import com.soundlab.dockerizedjavaapi.exceptions.InvalidCredentialsException;
import com.soundlab.dockerizedjavaapi.services.domain.UserService;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;
import com.soundlab.dockerizedjavaapi.utils.StringResources;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

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
            vagaService
                .findAll()
                .stream()
                .filter(vaga -> vaga.getStatus().equals(VagaStatus.ABERTO))
                .sorted((o1, o2) -> o2.getDateCreated().compareTo(o1.getDateCreated()))
                .limit(10)
                .collect(Collectors.toList())
        );
    }

    public User requestSignIn(String email, String password) {
        User user = userService.findByEmail(email);
        if (user == null || !password.equals(user.getPassword()))
            throw new InvalidCredentialsException();
        return user;
    }
}
