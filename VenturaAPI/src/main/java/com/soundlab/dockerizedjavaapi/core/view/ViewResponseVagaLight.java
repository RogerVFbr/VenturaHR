package com.soundlab.dockerizedjavaapi.core.view;

import lombok.Value;

@Value
public class ViewResponseVagaLight {
    Long id;
    String shortDescription;
    String longDescription;
    String city;
    String state;
}