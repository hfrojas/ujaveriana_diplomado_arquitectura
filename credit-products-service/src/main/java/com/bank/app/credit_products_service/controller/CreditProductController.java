package com.bank.app.credit_products_service.controller;

import com.bank.app.credit_products_service.dto.ResponseWrapper;
import com.bank.app.credit_products_service.service.CreditProductService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class CreditProductController {

    private CreditProductService creditProductService;

    @GetMapping("/credit-products")
    public ResponseWrapper getCreditProducts() {
        return creditProductService.getCreditProducts();
    }

}
