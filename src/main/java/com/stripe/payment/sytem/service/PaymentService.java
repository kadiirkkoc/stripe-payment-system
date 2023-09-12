package com.stripe.payment.sytem.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;
import com.stripe.net.StripeResponse;
import com.stripe.param.ChargeCreateParams;

import com.stripe.payment.sytem.dto.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${api.stripe.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public CustomerDto createCustomer(CustomerDto customerDto) throws StripeException {
        Map<String, Object> customerParams = new HashMap<>();
        customerParams.put("name",customerDto.getName());
        customerParams.put("email",customerDto.getEmail());
        customerParams.put("description",customerDto.getDescription());
        customerParams.put("phone",customerDto.getPhone());
        customerParams.put("address",customerDto.getAddress());

        Map<String, Object> metaData = new HashMap<>();
        metaData.put("id", customerDto.getCustomerId());
        metaData.putAll(customerDto.getAdditionalInfo());

        customerParams.put("metadata",metaData);

        Customer customer = Customer.create(customerParams);

        CustomerDto customerDto1 = new CustomerDto();
        customerDto1.setCustomerId(customer.getId());
        customerDto1.setName(customer.getName());
        customerDto1.setEmail(customer.getEmail());
        customerDto1.setDescription(customer.getDescription());
        customerDto1.setPhone(customer.getPhone());
        customerDto1.setAddress(customer.getAddress());

        return customerDto1;
    }

    public TokenDto createCardToken(TokenDto tokenDto) throws StripeException {
        try {
            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", "4242424242424242");
            cardParams.put("exp_month", 8);
            cardParams.put("exp_year", 2024);
            cardParams.put("cvc", "123");

//            cardParams.put("number", tokenDto.getNumber());
//            cardParams.put("exp_month", tokenDto.getExp_month());
//            cardParams.put("exp_year", tokenDto.getExp_year());
//            cardParams.put("cvc", tokenDto.getCvc());

            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("card", cardParams);

            Token token = Token.create(tokenParams);

            if (token != null && token.getId() != null) {
                tokenDto.setSuccess(true);
                tokenDto.setTokenId(token.getId());
                tokenDto.setExp_month(Math.toIntExact(token.getCard().getExpMonth()));
                tokenDto.setExp_year(Math.toIntExact(token.getCard().getExpYear()));
            }
            return tokenDto;
        } catch (StripeException e) {
            throw e;
        }
    }


    public PaymentDto createPaymentMethod(TokenDto tokenDto) throws StripeException {


        Map<String, Object> cardParams = new HashMap<>();
        cardParams.put("number", tokenDto.getNumber());
        cardParams.put("exp_month", tokenDto.getExp_month());
        cardParams.put("exp_year", tokenDto.getExp_year());
        cardParams.put("cvc", tokenDto.getCvc());

        Map<String, Object> params = new HashMap<>();
        params.put("type","card");
        params.put("card", cardParams);

        PaymentMethod paymentMethod = PaymentMethod.create(params);
        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setId(paymentMethod.getId());

        System.out.println("Created PaymentMethod ID: " + paymentMethod.getId());
        return paymentDto;
    }


    public Charge createCharge(ChargeDto chargeDto) throws StripeException{
            RequestOptions requestOptions = RequestOptions.builder().setApiKey(stripeApiKey).build();
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(chargeDto.getAmount())
                    .setCurrency(chargeDto.getCurrency())
                    .setCustomer(chargeDto.getCustomerId())
                    .setSource(chargeDto.getSource())
                    .build();

            Charge charge = Charge.create(params, requestOptions);

            return charge;
    }

//    public ResponseEntity<Object> attachTokenToCustomer(String customerId) throws StripeException {
//        PaymentMethod paymentMethod =
//                PaymentMethod.retrieve(
//                        "pm_1Np96oBI2NXRnicm870Jz7Lg"
//                );
//
//        Map<String, Object> params = new HashMap<>();
//        params.put("customer", "cus_OZkQGia7T8eY0B");
//
//        PaymentMethod updatedPaymentMethod =
//                paymentMethod.attach(params);
//
//    }

    public PaymentMethod abc() throws StripeException {
        Map<String, Object> card = new HashMap<>();
        card.put("number", "4242424242424242");
        card.put("exp_month", 12);
        card.put("exp_year", 2034);
        card.put("cvc", "314");
        Map<String, Object> params = new HashMap<>();
        params.put("type", "card");
        params.put("card", card);

        PaymentMethod paymentMethod =
                PaymentMethod.create(params);
        return paymentMethod;
    }

}
