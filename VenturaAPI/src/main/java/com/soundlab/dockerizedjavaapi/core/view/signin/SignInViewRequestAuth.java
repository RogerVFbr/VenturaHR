package com.soundlab.dockerizedjavaapi.core.view.signin;

import lombok.Data;

@Data
public class SignInViewRequestAuth {
    private String email;
    private String password;
}
