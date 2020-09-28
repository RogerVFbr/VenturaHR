package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.services.utils.StringResourcesService;

public abstract class ViewService<T> {

    protected StringResourcesService stringResourcesService;

    protected ViewService(StringResourcesService stringResourcesService) {
        this.stringResourcesService = stringResourcesService;
    }

    protected abstract T getContent();
}
