package com.soundlab.dockerizedjavaapi.models;

public enum Level {
    MUITO_BAIXO (0, "muito baixo"),
    BAIXO (1, "baixo"),
    MEDIO (2, "medio"),
    ALTO (3, "alto"),
    MUITO_ALTO (4, "muito alto");

    private final int id;
    private final String description;

    Level(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
