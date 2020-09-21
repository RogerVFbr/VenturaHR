package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.core.domain.vaga.VagaStatus;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.domain.UserService;
import com.soundlab.dockerizedjavaapi.services.domain.VagaService;

import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SignInViewService {

    private final VagaService vagaService;
    private final UserService userService;

    public SignInViewService(VagaService vagaService, UserService userService) {
        this.vagaService = vagaService;
        this.userService = userService;
    }

    public SignInViewResponseContent getContent() {
        return new SignInViewResponseContent(
            vagaService
                .findAll()
                .stream()
                .filter(vaga -> vaga.getStatus().equals(VagaStatus.ABERTO))
                .sorted((o1, o2) -> o2.getDateCreated().compareTo(o1.getDateCreated()))
                .limit(10)
                .collect(Collectors.toList())
        );
    }

    public boolean requestSignIn(String email, String password) {
        User user = userService.findByEmail(email);
        return user != null && password.equals(user.getPassword());
    }
}
