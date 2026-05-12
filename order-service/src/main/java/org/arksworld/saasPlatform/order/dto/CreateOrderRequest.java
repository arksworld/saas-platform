package org.arksworld.saasPlatform.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderRequest {
    private  String tenantId;
    private  String userId;
    private  double amount;
}
