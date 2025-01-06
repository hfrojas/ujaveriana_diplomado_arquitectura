package com.bank.app.credit_products_service.dto;

import com.bank.app.credit_products_service.model.Data;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseWrapper {
    @JsonProperty("status")
    private String status;
    @JsonProperty("server")
    private String server;
    @JsonProperty("data")
    private Data data;
    @JsonProperty("message")
    private String message;
}
