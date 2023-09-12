package com.stripe.payment.sytem.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.payment.sytem.dto.ChargeDto;
import com.stripe.payment.sytem.dto.CustomerDto;
import com.stripe.payment.sytem.dto.TokenDto;
import com.stripe.payment.sytem.service.PaymentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripe")
public class Controller {

    private final PaymentService paymentService;

    public Controller(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create/customer")
    public CustomerDto createCustomer(@RequestBody CustomerDto customerDto) throws StripeException {
          return paymentService.createCustomer(customerDto);
    }

    @PostMapping("/create/cardToken")
    public TokenDto createToken(@RequestBody TokenDto tokenDto) throws StripeException {
        return paymentService.createCardToken(tokenDto);
    }

    @PostMapping("/payment-method")
    public PaymentMethod createPaymentMethod() throws StripeException{
        return paymentService.createPaymentMethod();
    }

    @PostMapping("/charge")
    public Charge createCharge(@RequestBody ChargeDto chargeDto) throws StripeException{
        return paymentService.createCharge(chargeDto);
    }
}
