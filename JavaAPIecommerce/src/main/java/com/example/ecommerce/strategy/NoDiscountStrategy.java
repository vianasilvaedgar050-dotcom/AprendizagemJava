package com.example.ecommerce.strategy;

import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class NoDiscountStrategy implements DiscountStrategy {

    @Override
    public DiscountType type() {
        return DiscountType.NONE;
    }

    @Override
    public BigDecimal calculate(BigDecimal subtotal) {
        return BigDecimal.ZERO;
    }
}
