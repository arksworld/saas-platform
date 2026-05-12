package org.arksworld.saasPlatform.common.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BaseEvent<T> {
    private String eventId;
    private String eventType;
    private String tenantId;
    private long timestamp;
    private T payload;
}
