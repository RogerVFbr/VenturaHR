package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.domain.user.Candidato;
import com.soundlab.dockerizedjavaapi.core.domain.user.Empresa;
import com.soundlab.dockerizedjavaapi.core.domain.user.UserType;
import com.soundlab.dockerizedjavaapi.core.view.ViewResponseUserLight;
import com.soundlab.dockerizedjavaapi.core.view.signup.SignUpViewRequestUser;
import com.soundlab.dockerizedjavaapi.exceptions.InvalidRegistrationException;
import com.soundlab.dockerizedjavaapi.services.domain.CandidatoService;
import com.soundlab.dockerizedjavaapi.services.domain.EmpresaService;
import com.soundlab.dockerizedjavaapi.services.domain.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SignUpViewService {

    private final UserService userService;
    private final CandidatoService candidatoService;
    private final EmpresaService empresaService;
    private final ModelMapper modelMapper;

    public SignUpViewService(UserService userService,
                             CandidatoService candidatoService,
                             EmpresaService empresaService,
                             ModelMapper modelMapper) {
        this.userService = userService;
        this.candidatoService = candidatoService;
        this.empresaService = empresaService;
        this.modelMapper = modelMapper;
    }

    public ViewResponseUserLight requestSignUp(SignUpViewRequestUser user) {
        userService
            .findByDocumentIdOrEmail(user.getDocumentId(), user.getEmail())
            .ifPresent(checkedUser -> { throw new InvalidRegistrationException(); });

        if (user.getUserType() == UserType.CANDIDATO)
            candidatoService.save(modelMapper.map(user, Candidato.class));
        else if (user.getUserType() == UserType.EMPRESA)
            empresaService.save(modelMapper.map(user, Empresa.class));
        else
            throw new InvalidRegistrationException();

        return userService.findByEmail(user.getEmail(), ViewResponseUserLight.class);
    }
}
