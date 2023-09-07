package com.stripe.payment.sytem.dto;

import lombok.Data;

@Data
public class PaymentIntentDto {
    private Long amount;
    private String currency;
    private String customerId;
    private String paymentMethodId;
}
