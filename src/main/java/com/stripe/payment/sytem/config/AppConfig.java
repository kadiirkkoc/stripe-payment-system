package com.stripe.payment.sytem.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.stripe.model.Customer;
import com.stripe.payment.sytem.data.CustomerSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Customer.class, new CustomerSerializer());
        objectMapper.registerModule(module);
        return objectMapper;
    }
}
