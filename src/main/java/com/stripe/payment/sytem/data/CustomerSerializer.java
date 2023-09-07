package com.stripe.payment.sytem.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stripe.model.Customer;

import java.io.IOException;

public class CustomerSerializer extends JsonSerializer<Customer> {
    @Override
    public void serialize(Customer customer, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("subscriptions", String.valueOf(customer.getSubscriptions()));
        jsonGenerator.writeStringField("name", customer.getName());
        jsonGenerator.writeStringField("email", customer.getEmail());
        jsonGenerator.writeStringField("description", customer.getDescription());
        jsonGenerator.writeStringField("phone", customer.getPhone());
        jsonGenerator.writeStringField("additionalInfo", customer.getMetadata().toString());

        jsonGenerator.writeEndObject();
    }
}
