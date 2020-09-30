package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.repositories.UserRepository;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends GenericService<UserRepository, User> {
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public <T> T findByEmail(String email, Class<T> type) {
        return repository.findByEmail(email, type);
    }

    public Optional<User> findByDocumentIdOrEmail(String documentId, String email) {
        return repository.findByDocumentIdOrEmail(documentId, email);
    }
}
