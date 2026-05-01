package org.arksworld.saasPlatform.auth.kafka.service;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.auth.kafka.events.TenantCreatedEvent;
import org.arksworld.saasPlatform.auth.user.dto.UserRequest;
import org.arksworld.saasPlatform.auth.user.service.UserService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TenantEventConsumer {

    private final UserService userService;

    @KafkaListener(topics = "tenant-created", groupId = "saas-group")
    public void consume(TenantCreatedEvent event) {

        System.out.println("Received event:" + event);
        UserRequest userRequest = new UserRequest();
        userRequest.setTenantId(event.getTenantId());
        userRequest.setUsername(event.getAdminUsername());
        userRequest.setPassword(event.getAdminPassword());
        userRequest.setRole("ADMIN");
        userService.createUser(userRequest);
    }
}