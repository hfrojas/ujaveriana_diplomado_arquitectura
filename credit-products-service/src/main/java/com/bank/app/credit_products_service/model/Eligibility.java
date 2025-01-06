package com.bank.app.credit_products_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Eligibility {
    @JsonProperty("minIncome")
    private double minIncome;
    @JsonProperty("minCreditScore")
    private int minCreditScore;
}
