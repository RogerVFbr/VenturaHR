package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<UserRepository, User> {
    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public <T> T findByEmail(String email, Class<T> type) {
        return repository.findUserByEmail(email, type);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public User findByDocumentId(String documentId) {
        return repository.findByDocumentId(documentId);
    }
}
