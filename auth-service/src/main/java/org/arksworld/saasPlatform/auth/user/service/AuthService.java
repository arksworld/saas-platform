package org.arksworld.saasPlatform.auth.user.service;

import lombok.RequiredArgsConstructor;

import org.arksworld.saasPlatform.auth.user.entity.User;
import org.arksworld.saasPlatform.auth.user.repository.UserRepository;
import org.arksworld.saasPlatform.common.jwt.JwtUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;


    public String login(String username, String password, String tenantId) {

        User user = userRepository.findByUsernameAndTenantId(username, tenantId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid credentials");
        }

        return JwtUtil.generateToken(user.getUsername(), user.getTenantId());
    }
}