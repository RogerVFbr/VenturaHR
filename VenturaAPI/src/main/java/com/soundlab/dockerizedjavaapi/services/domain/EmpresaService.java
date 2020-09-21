package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.user.Empresa;
import com.soundlab.dockerizedjavaapi.repositories.EmpresaRepository;

import org.springframework.stereotype.Service;

@Service
public class EmpresaService extends GenericService<EmpresaRepository, Empresa> {
    public EmpresaService(EmpresaRepository empresaRepository) {
        super(empresaRepository);
    }
}
