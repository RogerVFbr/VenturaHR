package com.soundlab.dockerizedjavaapi.core.view.signin;

import com.soundlab.dockerizedjavaapi.core.domain.vaga.Vaga;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInViewResponseContent {
    private List<Vaga> vagas;
}
