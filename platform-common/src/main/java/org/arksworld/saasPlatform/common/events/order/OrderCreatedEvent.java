package org.arksworld.saasPlatform.common.events.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {
    private String orderId;
    private String tenantId;
    private String userId;
    //private List<OrderItem> items;
    private double amount;
}
