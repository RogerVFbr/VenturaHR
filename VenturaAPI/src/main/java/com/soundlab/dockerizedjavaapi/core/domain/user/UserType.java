package com.soundlab.dockerizedjavaapi.core.domain.user;

public enum UserType {
    CANDIDATO(0, "candidato"),
    EMPRESA(1, "empresa"),
    ADMINISTRADOR(2, "administrador");

    private final int id;
    private final String description;

    UserType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
