package com.stripe.payment.sytem.data;

import com.stripe.model.*;
import com.stripe.model.testhelpers.TestClock;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Customer implements HasId {

    private Address address;
    private Long balance;
    private CashBalance cashBalance;
    private Long created;
    private String currency;
    private ExpandableField<PaymentSource> defaultSource;
    private Boolean deleted;
    private Boolean delinquent;
    private String description;
    private Discount discount;
    private String email;
    private String id;
    private Map<String, Long> invoiceCreditBalance;
    private String invoicePrefix;
    private com.stripe.model.Customer.InvoiceSettings invoiceSettings;
    private Boolean livemode;
    private Map<String, String> metadata;
    private String name;
    private Long nextInvoiceSequence;
    private String object;
    private String phone;
    private List<String> preferredLocales;
    private ShippingDetails shipping;
    private PaymentSourceCollection sources;
    private SubscriptionCollection subscriptions;
    private com.stripe.model.Customer.Tax tax;
    private String taxExempt;
    private TaxIdCollection taxIds;
    private ExpandableField<TestClock> testClock;

}
