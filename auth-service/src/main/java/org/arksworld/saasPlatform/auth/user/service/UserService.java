package org.arksworld.saasPlatform.auth.user.service;

import org.arksworld.saasPlatform.auth.user.dto.UserRequest;
import org.arksworld.saasPlatform.auth.user.entity.User;
import org.arksworld.saasPlatform.auth.user.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(UserRequest request) {
        User user = new User(UUID.randomUUID().toString(), request.getUsername(), request.getPassword(), request.getTenantId(), request.getRole());
        return userRepository.save(user);
    }
}
