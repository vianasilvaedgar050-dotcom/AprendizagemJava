package com.example.ecommerce.strategy;

import com.example.ecommerce.config.CheckoutConfiguration;
import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.stereotype.Component;

@Component
public class LoyaltyDiscountStrategy implements DiscountStrategy {

    private final CheckoutConfiguration configuration;

    public LoyaltyDiscountStrategy(CheckoutConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public DiscountType type() {
        return DiscountType.LOYALTY;
    }

    @Override
    public BigDecimal calculate(BigDecimal subtotal) {
        return subtotal.multiply(configuration.getLoyaltyDiscount())
                .setScale(2, RoundingMode.HALF_UP);
    }
}
