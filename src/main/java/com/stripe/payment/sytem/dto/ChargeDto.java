package com.stripe.payment.sytem.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class ChargeDto {
    private String token;
    private String username;
    private Long amount;
    private String message;
    private Boolean success;
    private String chargeId;
    private Map<String,Object> additionalInfo = new HashMap<>();

}
