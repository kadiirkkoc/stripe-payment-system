package com.stripe.payment.sytem.data;

import com.stripe.net.HttpHeaders;
import lombok.Data;

import java.util.Map;

@Data
public class StripeResponse {
    private int statusCode;
    private Map<String, Object> headers;
    private String body;
}
