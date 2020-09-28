package com.soundlab.dockerizedjavaapi.mappers;

import com.soundlab.dockerizedjavaapi.core.domain.user.Empresa;
import com.soundlab.dockerizedjavaapi.core.view.signup.SignUpViewRequestUser;

import org.modelmapper.PropertyMap;

public class EmpresaFromSignUpRequestUserMapper extends PropertyMap<SignUpViewRequestUser, Empresa> {

    @Override
    protected void configure() {
        map().setId(null);
        map().setName(source.getName());
        map().setAddress(source.getAddress());
        map().setPhoneNumber(source.getPhoneNumber());
        map().setEmail(source.getEmail());
        map().setUserType(source.getUserType());
        map().setPassword(source.getPassword());
        map().setDocumentId(source.getDocumentId());
        map().setRazaoSocial(source.getRazaoSocial());
    }
}
