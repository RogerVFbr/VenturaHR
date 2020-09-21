package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewContent;
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

    public SignInViewContent getContent() {
        return new SignInViewContent(
            vagaService
                .findAll()
                .stream()
                .sorted((o1, o2) -> o2.getDateCreated().compareTo(o1.getDateCreated()))
                .limit(10)
                .collect(Collectors.toList())
        );
    }

    public boolean requestSignIn(String email, String password) {
        User user = userService.findByEmail(email);
        if (user != null && password.equals(user.getPassword()))
            return true;
        return false;
    }
}
