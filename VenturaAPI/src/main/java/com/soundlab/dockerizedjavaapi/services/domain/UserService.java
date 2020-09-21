package com.soundlab.dockerizedjavaapi.services.domain;

import com.soundlab.dockerizedjavaapi.core.domain.user.User;
import com.soundlab.dockerizedjavaapi.repositories.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService extends GenericService<UserRepository, User> {

    public UserService(UserRepository userRepository) {
        super(userRepository);
    }

    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }
}
