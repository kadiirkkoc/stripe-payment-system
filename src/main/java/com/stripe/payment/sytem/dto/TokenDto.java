package com.stripe.payment.sytem.dto;

import lombok.Data;
import lombok.Setter;

@Data
@Setter
public class TokenDto {
    private String number;
    private Integer  exp_month;
    private Integer  exp_year;
    private String cvc;
    private String tokenId;
    private boolean success;

}
