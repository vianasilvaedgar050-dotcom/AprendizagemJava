package com.example.ecommerce.config;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class CheckoutConfiguration {

    private final BigDecimal percentageDiscount = new BigDecimal("0.10");
    private final BigDecimal loyaltyDiscount = new BigDecimal("0.05");
    private final BigDecimal standardShippingFee = new BigDecimal("5.00");
    private final BigDecimal expressShippingFee = new BigDecimal("15.00");

    public BigDecimal getPercentageDiscount() {
        return percentageDiscount;
    }

    public BigDecimal getLoyaltyDiscount() {
        return loyaltyDiscount;
    }

    public BigDecimal getStandardShippingFee() {
        return standardShippingFee;
    }

    public BigDecimal getExpressShippingFee() {
        return expressShippingFee;
    }
}
