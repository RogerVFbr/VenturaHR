package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.models.Admin;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}
