package com.soundlab.dockerizedjavaapi.core.view.signup;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SignUpViewResponseContent {
    private String companyLogoText;
    private String pageFooterContent;
    private String signUpFormTitle;
}
