package com.example.ecommerce.strategy;

import java.math.BigDecimal;

public interface ShippingStrategy {

    ShippingType type();

    BigDecimal calculateFee();
}
