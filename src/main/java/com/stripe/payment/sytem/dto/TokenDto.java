package com.stripe.payment.sytem.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class TokenDto {
    private String number;
    private int exp_month;
    private int exp_year;
    private String cvc;
    private String tokenId;
    private String username;
    private boolean success;
    private String message;
}
