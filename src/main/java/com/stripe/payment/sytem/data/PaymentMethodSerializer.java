package com.stripe.payment.sytem.data;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.stripe.model.PaymentMethod;

import java.io.IOException;

public class PaymentMethodSerializer extends JsonSerializer<PaymentMethod> {
    @Override
    public void serialize(PaymentMethod paymentMethod, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // Serialize PaymentMethod excluding the lastResponse property
        paymentMethod.setLastResponse(null);
        serializerProvider.defaultSerializeValue(paymentMethod, jsonGenerator);

        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("type", String.valueOf(paymentMethod.getType()));
        jsonGenerator.writeStringField("card[token]", String.valueOf(paymentMethod.getCard()));
        jsonGenerator.writeEndObject();
    }
}
