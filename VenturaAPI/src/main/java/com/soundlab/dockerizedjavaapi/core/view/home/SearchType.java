package com.soundlab.dockerizedjavaapi.core.view.home;

public enum SearchType {
    ALL(0, "all"),
    ANY(1, "any"),
    NONE(2, "none");

    private final int id;
    private final String description;

    SearchType(int id, String description) {
        this.id = id;
        this.description = description;
    }
}
