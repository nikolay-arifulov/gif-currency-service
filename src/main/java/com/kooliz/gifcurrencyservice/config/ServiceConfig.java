package com.kooliz.gifcurrencyservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class ServiceConfig {

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }
}
