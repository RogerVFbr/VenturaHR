package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.view.ViewResponseUserLight;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewRequestAuth;
import com.soundlab.dockerizedjavaapi.core.view.signin.SignInViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.view.SignInViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(
    name = "View: Sign In",
    description = "Gerencia informações de view."
)
public class SignInViewController {

    SignInViewService signInViewService;

    public SignInViewController(SignInViewService signInViewService) {
        this.signInViewService = signInViewService;
    }

    @GetMapping("/sign-in/content")
    public ResponseEntity<SignInViewResponseContent> getContent() {
        return ResponseEntity.ok(signInViewService.getContent());
    }

    @PostMapping("/sign-in/auth")
    public ResponseEntity<ViewResponseUserLight> requestSignIn(@RequestBody SignInViewRequestAuth request) {
        return ResponseEntity.ok(signInViewService.requestSignIn(request.getEmail(), request.getPassword()));
    }
}
