package com.soundlab.dockerizedjavaapi.repositories;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    <T> T findUserByEmail(String email, Class<T> type);
    User findByEmail(String email);
    User findByDocumentId(String documentId);
}
