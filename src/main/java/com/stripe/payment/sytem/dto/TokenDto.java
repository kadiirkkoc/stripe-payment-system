package com.stripe.payment.sytem.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class TokenDto {
    private String cardNumber;
    private int expMonth;
    private int expYear;
    private String cvc;
    private String token;
    private boolean success;

}
