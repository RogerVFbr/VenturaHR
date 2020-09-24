package com.soundlab.dockerizedjavaapi.core.view.signin;

public interface SignInViewResponseUser {
    Long getId();
    String getName();
    String getDocumentId();
    String getEmail();
    String getPassword();
}