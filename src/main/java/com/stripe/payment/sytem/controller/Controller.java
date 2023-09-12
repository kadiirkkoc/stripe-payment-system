package com.stripe.payment.sytem.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.*;
import com.stripe.payment.sytem.dto.*;
import com.stripe.payment.sytem.service.PaymentService;
import org.springframework.web.bind.annotation.*;

import static java.util.Objects.nonNull;

@RestController
@RequestMapping("/stripe")
public class Controller {

    private final PaymentService paymentService;

    public Controller(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/card/token")
    @ResponseBody
    public TokenDto createCardToken(@RequestBody TokenDto model) throws StripeException {
        return paymentService.createCardToken(model);
    }

    @PostMapping("/charge")
    @ResponseBody
    public ChargeDto charge(@RequestBody ChargeDto model) {
        return paymentService.createCharge(model);
    }

    @PostMapping("/customer/subscription")
    @ResponseBody
    public SubscriptionResponse subscription(@RequestBody SubscriptionDto model) throws StripeException {
        return paymentService.createSubscription(model);
    }


}
