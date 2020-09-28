package com.soundlab.dockerizedjavaapi.core.view.signup;

import com.soundlab.dockerizedjavaapi.core.domain.user.UserType;

import lombok.Data;

@Data
public class SignUpViewRequestUser {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private UserType userType;
    private String documentId;
    private String razaoSocial;
}
