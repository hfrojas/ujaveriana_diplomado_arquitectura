package com.bank.app.credit_products_service.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class RestTemplateLoggingInterceptor implements ClientHttpRequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateLoggingInterceptor.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Log the request details
        logger.info("Request Method: {}, Request URL: {}, Request Body: {}", request.getMethod(), request.getURI(), new String(body));

        // Execute the request
        ClientHttpResponse response = execution.execute(request, body);

        // Log the response details
        String responseBody = new String(response.getBody().readAllBytes());
        logger.info("Response Status: {}, Response Body: {}", response.getStatusCode(), responseBody);

        return response;
    }
}
