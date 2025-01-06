package com.bank.app.credit_products_service.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Data {
    @JsonProperty("creditProducts")
    private List<CreditProduct> creditProducts;
}
