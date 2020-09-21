package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.user.Admin;
import com.soundlab.dockerizedjavaapi.repositories.AdminRepository;

import org.springframework.stereotype.Service;

@Service
public class AdminService extends GenericService<AdminRepository, Admin> {
    public AdminService(AdminRepository adminRepository) {
        super(adminRepository);
    }
}
