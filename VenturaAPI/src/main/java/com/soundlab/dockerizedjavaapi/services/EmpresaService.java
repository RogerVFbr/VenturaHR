package com.soundlab.dockerizedjavaapi.services;

import com.soundlab.dockerizedjavaapi.core.user.Empresa;
import com.soundlab.dockerizedjavaapi.repositories.EmpresaRepository;

import org.springframework.stereotype.Service;

@Service
public class EmpresaService extends UserService<EmpresaRepository, Empresa> {

    public EmpresaService(EmpresaRepository empresaRepository) {
        super(empresaRepository);
    }
}
