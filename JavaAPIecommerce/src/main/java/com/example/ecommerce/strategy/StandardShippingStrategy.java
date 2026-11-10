package com.example.ecommerce.strategy;

import com.example.ecommerce.config.CheckoutConfiguration;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class StandardShippingStrategy implements ShippingStrategy {

    private final CheckoutConfiguration configuration;

    public StandardShippingStrategy(CheckoutConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public ShippingType type() {
        return ShippingType.STANDARD;
    }

    @Override
    public BigDecimal calculateFee() {
        return configuration.getStandardShippingFee();
    }
}
