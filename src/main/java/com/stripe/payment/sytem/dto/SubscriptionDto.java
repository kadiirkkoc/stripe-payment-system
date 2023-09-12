package com.stripe.payment.sytem.dto;

import lombok.Data;

@Data
public class SubscriptionDto {
    private String cardNumber;
    private int exp_month;
    private int exp_year;
    private String cvc;
    private String email;
    private String priceId;
    private String username;
    private long numberOfLisence;
    private boolean success;
}
