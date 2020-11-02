package com.soundlab.dockerizedjavaapi.controllers.view;

import com.soundlab.dockerizedjavaapi.core.view.ViewResponseUserLight;
import com.soundlab.dockerizedjavaapi.core.view.signup.SignUpViewRequestUser;
import com.soundlab.dockerizedjavaapi.services.view.SignUpViewService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpViewController {

    SignUpViewService signUpViewService;

    public SignUpViewController(SignUpViewService signUpViewService) {
        this.signUpViewService = signUpViewService;
    }

    @PostMapping("/sign-up/register")
    public ResponseEntity<ViewResponseUserLight> requestSignUp(@RequestBody SignUpViewRequestUser signUpRequest) {
        return ResponseEntity.ok(signUpViewService.requestSignUp(signUpRequest));
    }
}
