package com.soundlab.dockerizedjavaapi.services.utils;

import org.springframework.stereotype.Service;

@Service
public class StringResourcesService extends ValueVaultService {
    public StringResourcesService() {
        super();
    }

    public String getCompanyLogoText() {
        return getResource("COMPANY_LOGO_TEXT");
    }

    public String getPageFooterText() {
        return getResource("PAGE_FOOTER_TEXT");
    }

    public String getMemberBenefits() {
        return getResource("MEMBER_BENEFITS");
    }

    public String getSignUpFormTitle() {
        return getResource("SIGN_UP_FORM_TITLE");
    }
}