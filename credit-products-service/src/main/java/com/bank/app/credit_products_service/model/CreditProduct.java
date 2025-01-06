package com.bank.app.credit_products_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditProduct {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("type")
    private String type;
    @JsonProperty("description")
    private String description;
    @JsonProperty("interestRate")
    private double interestRate;
    @JsonProperty("maxLoanAmount")
    private double maxLoanAmount;
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("eligibility")
    private Eligibility eligibility;

}
