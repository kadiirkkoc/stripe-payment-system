package com.stripe.payment.sytem.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.*;

import com.stripe.payment.sytem.dto.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${api.stripe.key}")
    private String stripeApiKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeApiKey;
    }

    public TokenDto createCardToken(TokenDto tokenDto) throws StripeException {

        try {
            Map<String, Object> card = new HashMap<>();
            card.put("number", tokenDto.getCardNumber());
            card.put("exp_month", tokenDto.getExpMonth());
            card.put("exp_year", tokenDto.getExpYear());
            card.put("cvc", tokenDto.getCvc());
            Map<String, Object> params = new HashMap<>();
            params.put("card", card);
            Token token = Token.create(params);
            if (token != null && token.getId() != null) {
                tokenDto.setSuccess(true);
                tokenDto.setToken(token.getId());
            }
            return tokenDto;
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    private Customer createCustomer(PaymentMethod paymentMethod,SubscriptionDto subscriptionDto){

        try {

            Map<String, Object> customerMap = new HashMap<>();
            customerMap.put("name", subscriptionDto.getUsername());
            customerMap.put("email", subscriptionDto.getEmail());
            customerMap.put("payment_method", paymentMethod.getId());

            return Customer.create(customerMap);
        } catch (StripeException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

    public ChargeDto createCharge(ChargeDto chargeDto) {
        try {
            chargeDto.setSuccess(false);
            Map<String, Object> params = new HashMap<>();
            params.put("amount", chargeDto.getAmount());
            params.put("currency", "usd");
            params.put("source", chargeDto.getToken());
            params.put(
                    "description",
                    "payment for id" + chargeDto.getAdditionalInfo().getOrDefault("id_tag","")
            );
            Map<String , Object> metaData = new HashMap<>();
            metaData.put("id",chargeDto.getChargeId());
            metaData.putAll(chargeDto.getAdditionalInfo());
            params.put("metadata",metaData);
            Charge charge = Charge.create(params);
            chargeDto.setMessage(charge.getOutcome().getSellerMessage());

            if (charge.getPaid()){
                chargeDto.setChargeId(charge.getId());
                chargeDto.setSuccess(true);
            }
            return chargeDto;
        }
        catch (StripeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public SubscriptionResponse createSubscription(SubscriptionDto subscriptionDto) throws StripeException {
        PaymentMethod paymentMethod = createPaymentMethod(subscriptionDto);
        Customer customer = createCustomer(paymentMethod, subscriptionDto);
        paymentMethod = attachCustomerToPaymentMethod(customer, paymentMethod);
        Subscription subscription = createSubscription(subscriptionDto, paymentMethod, customer);

        return createResponse(subscriptionDto,paymentMethod,customer,subscription);
    }

    private PaymentMethod createPaymentMethod(SubscriptionDto subscriptionDto){
        try {
            Map<String, Object> cardParams = new HashMap<>();
            cardParams.put("number", subscriptionDto.getCardNumber());
            cardParams.put("exp_month", subscriptionDto.getExp_month());
            cardParams.put("exp_year", subscriptionDto.getExp_year());
            cardParams.put("cvc", subscriptionDto.getCvc());

            Map<String, Object> params = new HashMap<>();
            params.put("type","card");
            params.put("card", cardParams);

            return PaymentMethod.create(params);
        }catch (StripeException e){
            throw new RuntimeException(e.getMessage());
        }
    }

    private SubscriptionResponse createResponse(SubscriptionDto subscriptionDto,PaymentMethod paymentMethod,
                                                Customer customer,Subscription subscription){

        return SubscriptionResponse.builder()
                .username(subscriptionDto.getUsername())
                .PaymentMethodId(paymentMethod.getId())
                .SubscriptionId(subscription.getId())
                .CustomerId(customer.getId())
                .build();
    }


    private PaymentMethod attachCustomerToPaymentMethod(Customer customer,PaymentMethod paymentMethod){
        try {
            paymentMethod = PaymentMethod.retrieve(paymentMethod.getId());
            Map<String,Object> params = new HashMap<>();
            params.put("customer",customer.getId());
            paymentMethod = paymentMethod.attach(params);
            return paymentMethod;

        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }

    private Subscription createSubscription(SubscriptionDto subscriptionDto,PaymentMethod paymentMethod,Customer customer){
        try {
            List<Object> items = new ArrayList<>();
            Map<String, Object> item1 = new HashMap<>();
            item1.put(
                    "price",
                    subscriptionDto.getPriceId()
            );
            item1.put("quantity",subscriptionDto.getNumberOfLisence());
            items.add(item1);

            Map<String, Object> params = new HashMap<>();
            params.put("customer", customer.getId());
            params.put("default_payment_method", paymentMethod.getId());
            params.put("items", items);
            return Subscription.create(params);

        } catch (StripeException e) {
            throw new RuntimeException(e);
        }
    }
}
