package com.stripe.payment.sytem.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubscriptionResponse {
    private String CustomerId;
    private String SubscriptionId;
    private String PaymentMethodId;
    private String username;
}
