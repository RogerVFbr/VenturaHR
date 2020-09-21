package com.soundlab.dockerizedjavaapi.core.view.signin;

import lombok.Data;

@Data
public class SignInAuthRequest {
    private String email;
    private String password;
}
