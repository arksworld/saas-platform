package org.arksworld.saasPlatform.auth.kafka.handler;

import lombok.RequiredArgsConstructor;
import org.arksworld.saasPlatform.auth.user.entity.User;
import org.arksworld.saasPlatform.auth.user.repository.UserRepository;
import org.arksworld.saasPlatform.common.events.BaseEvent;
import org.arksworld.saasPlatform.common.events.tenant.TenantCreatedEvent;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class TenantEventHandler {

    private final UserRepository userRepository;

    public void handleTenantCreated(BaseEvent event, TenantCreatedEvent payload) {
        System.out.println("Creating admin user for tenant: " + payload.getTenantId());
        User user = new User(UUID.randomUUID().toString(), payload.getAdminUsername(),
                payload.getAdminPassword(), payload.getTenantId(), "ADMIN");
        userRepository.save(user);
    }
}