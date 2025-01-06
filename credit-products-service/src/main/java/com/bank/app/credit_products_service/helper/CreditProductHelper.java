package com.bank.app.credit_products_service.helper;

import com.bank.app.credit_products_service.dto.ResponseWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Component
public class CreditProductHelper {

    @Value("${credit-products.url}")
    private String creditProductsUrl;

    @Value("${contingency-credit-products.url}")
    private String contingencyCreditProductsUrl;

    private final RestTemplate restTemplate;

    private final ObjectMapper objectMapper;

    public CreditProductHelper(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    public ResponseWrapper callCreditProducts(){
        String response = restTemplate.getForObject(creditProductsUrl, String.class);

        try {
            return objectMapper.readValue(response, ResponseWrapper.class);
        } catch (Exception e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error al mapear la respuesta JSON",
                    e
            );
        }
    }

    public ResponseWrapper callContingencyCreditProducts() {
        ResponseEntity response = restTemplate.getForEntity(contingencyCreditProductsUrl, String.class);
        var jsonResponse = (String) response.getBody();

        try {
            return objectMapper.readValue(jsonResponse, ResponseWrapper.class);
        } catch (Exception e) {
            throw new RuntimeException("Error al mapear la respuesta JSON del fallback", e);
        }
    }


}
