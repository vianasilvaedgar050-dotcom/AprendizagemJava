package com.example.ecommerce.strategy;

import com.example.ecommerce.config.CheckoutConfiguration;
import java.math.BigDecimal;
import org.springframework.stereotype.Component;

@Component
public class ExpressShippingStrategy implements ShippingStrategy {

    private final CheckoutConfiguration configuration;

    public ExpressShippingStrategy(CheckoutConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public ShippingType type() {
        return ShippingType.EXPRESS;
    }

    @Override
    public BigDecimal calculateFee() {
        return configuration.getExpressShippingFee();
    }
}
