package com.stripe.payment.sytem.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String number;
    private int exp_month;
    private int exp_year;
    private String cvc;
    private String token;
}
