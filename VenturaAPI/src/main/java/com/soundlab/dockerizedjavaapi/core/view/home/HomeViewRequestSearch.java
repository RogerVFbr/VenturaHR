package com.soundlab.dockerizedjavaapi.core.view.home;

import lombok.Data;

@Data
public class HomeViewRequestSearch {
    private String content;
    private SearchType type;
}
