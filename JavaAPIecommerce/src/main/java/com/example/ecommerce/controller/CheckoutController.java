package com.example.ecommerce.controller;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.dto.CheckoutRequest;
import com.example.ecommerce.service.CheckoutFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CheckoutController {

    private final CheckoutFacade checkoutFacade;

    public CheckoutController(CheckoutFacade checkoutFacade) {
        this.checkoutFacade = checkoutFacade;
    }

    @PostMapping("/checkout")
    @ResponseStatus(HttpStatus.CREATED)
    public Order checkout(@Valid @RequestBody CheckoutRequest request) {
        return checkoutFacade.checkout(request);
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable String id) {
        return checkoutFacade.getOrder(id);
    }
}
