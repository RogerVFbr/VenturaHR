package com.soundlab.dockerizedjavaapi.core.view.signin;

import com.soundlab.dockerizedjavaapi.core.view.ViewResponseVagaLight;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInViewResponseContent {
    private String memberBenefits;
    private List<ViewResponseVagaLight> vagas;
}

