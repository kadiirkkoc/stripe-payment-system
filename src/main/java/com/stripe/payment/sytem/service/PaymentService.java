package com.stripe.payment.sytem.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.net.RequestOptions;
import com.stripe.param.ChargeCreateParams;
import com.stripe.payment.sytem.dto.ChargeDto;
import com.stripe.payment.sytem.dto.CustomerDto;
import com.stripe.payment.sytem.dto.PaymentIntentDto;
import com.stripe.payment.sytem.dto.TokenDto;
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

    public Customer createCustomer(CustomerDto customerDto) throws StripeException {
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
        return customer;
    }

    public Token createCardToken(TokenDto tokenDto) throws StripeException{
        Map<String, Object> card = new HashMap<>();
        card.put("number", tokenDto.getNumber());
        card.put("exp_month", tokenDto.getExp_month());
        card.put("exp_year", tokenDto.getExp_year());
        card.put("cvc", tokenDto.getCvc());
        Map<String, Object> params = new HashMap<>();
        params.put("card", card);

        Token token = Token.create(params);
        return token;
    }

    public PaymentMethod createPaymentMethod(TokenDto tokenDto) throws StripeException {
        Map<String, Object> card = new HashMap<>();
        card.put("number", tokenDto.getNumber());
        card.put("exp_month", tokenDto.getExp_month());
        card.put("exp_year", tokenDto.getExp_year());
        card.put("cvc", tokenDto.getCvc());
        Map<String, Object> params = new HashMap<>();
        params.put("type","card");
        params.put("card", card);

        PaymentMethod paymentMethod = PaymentMethod.create(params);
        System.out.println("Created PaymentMethod ID: " + paymentMethod.getId());
        return paymentMethod;
    }

    public PaymentIntent createPaymentIntent(PaymentIntentDto paymentIntentDto) throws StripeException {
        Map<String, Object> automaticPaymentMethods = new HashMap<>();
        automaticPaymentMethods.put("enabled", true);
        Map<String, Object> params = new HashMap<>();
        params.put("amount", paymentIntentDto.getAmount());
        params.put("currency", paymentIntentDto.getCurrency());
        params.put(
                "automatic_payment_methods",
                automaticPaymentMethods
        );

        PaymentIntent paymentIntent = PaymentIntent.create(params);
        return paymentIntent;
    }

    public Charge createCharge(ChargeDto chargeDto) throws StripeException{
        try {
            RequestOptions requestOptions = RequestOptions.builder().setApiKey(stripeApiKey).build();
            ChargeCreateParams params = ChargeCreateParams.builder()
                    .setAmount(chargeDto.getAmount())
                    .setCurrency(chargeDto.getCurrency())
                    .setSource(chargeDto.getTestToken())
                    .build();

            Charge charge = Charge.create(params, requestOptions);

            System.out.println("Ödeme başarılı! Ödeme ID: " + charge.getId());
            return charge;
        } catch (StripeException e) {
            e.printStackTrace();
            return null;
        }
    }


}
