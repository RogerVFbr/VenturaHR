package com.soundlab.dockerizedjavaapi.core.view.signin;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignInViewResponseContent {
    private String companyLogoText;
    private String pageFooterContent;
    private String memberBenefits;
    private List<SignInViewResponseVaga> vagas;
}

