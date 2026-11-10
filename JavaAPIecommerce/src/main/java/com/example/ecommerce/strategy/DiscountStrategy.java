package com.example.ecommerce.strategy;

import java.math.BigDecimal;

public interface DiscountStrategy {

    DiscountType type();

    BigDecimal calculate(BigDecimal subtotal);
}
