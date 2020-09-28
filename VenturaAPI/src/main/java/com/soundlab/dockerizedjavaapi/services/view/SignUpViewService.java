package com.soundlab.dockerizedjavaapi.services.view;

import com.soundlab.dockerizedjavaapi.core.domain.user.Candidato;
import com.soundlab.dockerizedjavaapi.core.domain.user.Empresa;
import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.core.view.ViewResponseUser;
import com.soundlab.dockerizedjavaapi.core.view.signup.SignUpViewRequestUser;
import com.soundlab.dockerizedjavaapi.core.view.signup.SignUpViewResponseContent;
import com.soundlab.dockerizedjavaapi.exceptions.InvalidRegistrationException;
import com.soundlab.dockerizedjavaapi.services.domain.CandidatoService;
import com.soundlab.dockerizedjavaapi.services.domain.EmpresaService;
import com.soundlab.dockerizedjavaapi.services.domain.UserService;
import com.soundlab.dockerizedjavaapi.services.utils.StringResourcesService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class SignUpViewService extends ViewService<SignUpViewResponseContent>  {

    private final UserService userService;
    private final CandidatoService candidatoService;
    private final EmpresaService empresaService;
    private final ModelMapper modelMapper;

    public SignUpViewService(UserService userService,
                             CandidatoService candidatoService,
                             EmpresaService empresaService,
                             ModelMapper modelMapper,
                             StringResourcesService stringResourcesService) {
        super(stringResourcesService);
        this.userService = userService;
        this.candidatoService = candidatoService;
        this.empresaService = empresaService;
        this.modelMapper = modelMapper;
    }

    @Override
    public SignUpViewResponseContent getContent() {
        return new SignUpViewResponseContent(
            stringResourcesService.getCompanyLogoText(),
            stringResourcesService.getPageFooterText(),
            stringResourcesService.getSignUpFormTitle()
        );
    }

    public ViewResponseUser requestSignUp(SignUpViewRequestUser user) {
        User checkDocumentIdUser = userService.findByDocumentId(user.getDocumentId());
        User checkEmailUser = userService.findByEmail(user.getEmail());

        if (checkDocumentIdUser != null || checkEmailUser != null)
            throw new InvalidRegistrationException();

        switch(user.getUserType()) {
            case CANDIDATO:
                candidatoService.save(modelMapper.map(user, Candidato.class));
                break;
            case EMPRESA:
                empresaService.save(modelMapper.map(user, Empresa.class));
                break;
            default:
                throw new InvalidRegistrationException();
        }

        return userService.findByEmail(user.getEmail(), ViewResponseUser.class);
    }
}
