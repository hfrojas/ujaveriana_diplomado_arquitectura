package com.bank.app.credit_products_service.service;

import com.bank.app.credit_products_service.dto.ResponseWrapper;
import com.bank.app.credit_products_service.helper.CreditProductHelper;
import lombok.AllArgsConstructor;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CreditProductService {

    private CreditProductHelper helper;

    private CircuitBreakerFactory circuitBreakerFactory;

    public ResponseWrapper getCreditProducts() {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
        return circuitBreaker.run(() -> helper.callCreditProducts(), throwable -> getCreditProductsFallback());
    }

    public ResponseWrapper getCreditProductsFallback() {
        return helper.callContingencyCreditProducts();
    }
}
