package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.view.ViewResponseUser;
import com.soundlab.dockerizedjavaapi.core.view.signup.SignUpViewRequestUser;
import com.soundlab.dockerizedjavaapi.core.view.signup.SignUpViewResponseContent;
import com.soundlab.dockerizedjavaapi.services.view.SignUpViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpViewController {

    SignUpViewService signUpViewService;

    public SignUpViewController(SignUpViewService signUpViewService) {
        this.signUpViewService = signUpViewService;
    }

    @GetMapping("/sign-up/content")
    public ResponseEntity<SignUpViewResponseContent> getContent() {
        return ResponseEntity.ok(signUpViewService.getContent());
    }

    @PostMapping("/sign-up/register")
    public ResponseEntity<ViewResponseUser> requestSignUp(@RequestBody SignUpViewRequestUser signUpRequest) {
        ViewResponseUser user = signUpViewService.requestSignUp(signUpRequest);
        return ResponseEntity.ok(user);
    }
}
