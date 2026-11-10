package com.example.ecommerce.strategy;

import com.example.ecommerce.config.CheckoutConfiguration;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class PercentageDiscountStrategy implements DiscountStrategy {

    private final CheckoutConfiguration configuration;

    public PercentageDiscountStrategy(CheckoutConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public DiscountType type() {
        return DiscountType.PERCENTAGE;
    }

    @Override
    public BigDecimal calculate(BigDecimal subtotal) {
        return subtotal.multiply(configuration.getPercentageDiscount())
                .setScale(2, RoundingMode.HALF_UP);
    }
}
