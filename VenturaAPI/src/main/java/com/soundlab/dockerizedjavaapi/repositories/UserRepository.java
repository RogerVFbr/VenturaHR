package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    <T> T findByEmail(String email, Class<T> type);
    Optional<User> findByDocumentIdOrEmail(String documentId, String email);
}
