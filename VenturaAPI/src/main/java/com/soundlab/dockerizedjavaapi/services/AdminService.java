package com.soundlab.dockerizedjavaapi.services;

import com.soundlab.dockerizedjavaapi.models.Admin;
import com.soundlab.dockerizedjavaapi.repositories.AdminRepository;

import org.springframework.stereotype.Service;

@Service
public class AdminService extends UserService<AdminRepository, Admin> {
    public AdminService(AdminRepository adminRepository) {
        super(adminRepository);
    }
}
