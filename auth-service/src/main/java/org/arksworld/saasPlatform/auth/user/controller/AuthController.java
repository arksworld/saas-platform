package org.arksworld.saasPlatform.auth.user.controller;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.auth.user.dto.UserRequest;
import org.arksworld.saasPlatform.auth.user.entity.User;
import org.arksworld.saasPlatform.auth.user.service.AuthService;
import org.arksworld.saasPlatform.auth.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserService userService;

    @PostMapping("/login")
    public String login(@RequestBody Map<String, String> request) {
        return authService.login(
                request.get("username"),
                request.get("password"),
                request.get("tenantId")
        );
    }

    @PostMapping("/users")
    public User createUser(@RequestBody UserRequest request) {
        return userService.createUser(request);
    }
}