package com.stripe.payment.sytem.dto;

import com.stripe.model.Address;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class CustomerDto {
    private String customerId;
    private String name;
    private String email;
    private String description;
    private String phone;
    private Address address;
    private Map<String,Object> additionalInfo = new HashMap<>();
}
