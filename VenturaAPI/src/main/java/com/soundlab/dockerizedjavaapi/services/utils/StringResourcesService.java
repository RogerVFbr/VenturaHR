package com.soundlab.dockerizedjavaapi.services.utils;

import org.springframework.stereotype.Service;

@Service
public class StringResourcesService extends ValueVaultService {
    public StringResourcesService() {
        super();
    }

    public String getMemberBenefits() {
        return getResource("MEMBER_BENEFITS");
    }


    public String getSystemMessages() {
        return getResource("SYSTEM_MESSAGES");
    }
}