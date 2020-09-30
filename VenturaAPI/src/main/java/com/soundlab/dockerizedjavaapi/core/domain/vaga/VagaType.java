package com.soundlab.dockerizedjavaapi.core.domain.vaga;

public enum VagaType {
    CLT(0, "CLT"),
    PJ(1, "PJ");

    private final int id;
    private final String description;

    VagaType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
