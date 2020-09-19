package com.soundlab.dockerizedjavaapi.models;

public enum VagaStatus {
    ABERTO(0, "aberto"),
    ENCERRADO(1, "encerrado");

    private final int id;
    private final String description;

    VagaStatus(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
