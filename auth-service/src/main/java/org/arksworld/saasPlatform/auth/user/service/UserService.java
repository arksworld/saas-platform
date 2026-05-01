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

        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword()); // hash later
        user.setTenantId(request.getTenantId());
        user.setRole(request.getRole());

        return userRepository.save(user);
    }
}
