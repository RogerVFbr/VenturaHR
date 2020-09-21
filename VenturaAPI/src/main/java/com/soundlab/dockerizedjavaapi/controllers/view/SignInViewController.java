package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewRequestAuth;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.view.SignInViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignInViewController {

    SignInViewService signInViewService;

    public SignInViewController(SignInViewService signInViewService) {
        this.signInViewService = signInViewService;
    }

    @GetMapping("/sign-in/content")
    public ResponseEntity<SignInViewResponseContent> SignInGetContent() {
        return ResponseEntity.ok(signInViewService.getContent());
    }

    @PostMapping("/sign-in/auth")
    public ResponseEntity<User> requestSignIn(@RequestBody SignInViewRequestAuth authRequest) {
        User user = signInViewService.requestSignIn(authRequest.getEmail(), authRequest.getPassword());
        if (user != null) return ResponseEntity.ok(user);
        return ResponseEntity.notFound().build();
    }
}
