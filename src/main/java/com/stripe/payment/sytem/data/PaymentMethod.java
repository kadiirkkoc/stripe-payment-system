package com.stripe.payment.sytem.data;

import com.stripe.model.Card;
import com.stripe.model.ExpandableField;
import lombok.Data;

import java.util.Map;

@Data
public class PaymentMethod {
    private com.stripe.model.PaymentMethod.AcssDebit acssDebit;
    private com.stripe.model.PaymentMethod.Affirm affirm;
    private com.stripe.model.PaymentMethod.AfterpayClearpay afterpayClearpay;
    private com.stripe.model.PaymentMethod.Alipay alipay;
    private com.stripe.model.PaymentMethod.AuBecsDebit auBecsDebit;
    private com.stripe.model.PaymentMethod.BacsDebit bacsDebit;
    private com.stripe.model.PaymentMethod.Bancontact bancontact;
    private com.stripe.model.PaymentMethod.BillingDetails billingDetails;
    private com.stripe.model.PaymentMethod.Blik blik;
    private com.stripe.model.PaymentMethod.Boleto boleto;
    private Card card;
    private com.stripe.model.PaymentMethod.CardPresent cardPresent;
    private com.stripe.model.PaymentMethod.Cashapp cashapp;
    private Long created;
    private ExpandableField<Customer> customer;
    private com.stripe.model.PaymentMethod.CustomerBalance customerBalance;
    private com.stripe.model.PaymentMethod.Eps eps;
    private com.stripe.model.PaymentMethod.Fpx fpx;
    private com.stripe.model.PaymentMethod.Giropay giropay;
    private com.stripe.model.PaymentMethod.Grabpay grabpay;
    private String id;
    private com.stripe.model.PaymentMethod.Ideal ideal;
    private com.stripe.model.PaymentMethod.InteracPresent interacPresent;
    private com.stripe.model.PaymentMethod.Klarna klarna;
    private com.stripe.model.PaymentMethod.Konbini konbini;
    private com.stripe.model.PaymentMethod.Card.Wallet.Link link;
    private Boolean livemode;
    private Map<String, String> metadata;
    private String object;
    private com.stripe.model.PaymentMethod.Oxxo oxxo;
    private com.stripe.model.PaymentMethod.P24 p24;
    private com.stripe.model.PaymentMethod.Paynow paynow;
    private com.stripe.model.PaymentMethod.Paypal paypal;
    private com.stripe.model.PaymentMethod.Pix pix;
    private com.stripe.model.PaymentMethod.Promptpay promptpay;
    private com.stripe.model.PaymentMethod.RadarOptions radarOptions;
    private com.stripe.model.PaymentMethod.SepaDebit sepaDebit;
    private com.stripe.model.PaymentMethod.Sofort sofort;
    private String type;
    private com.stripe.model.PaymentMethod.UsBankAccount usBankAccount;
    private com.stripe.model.PaymentMethod.WechatPay wechatPay;
    private com.stripe.model.PaymentMethod.Zip zip;

}
