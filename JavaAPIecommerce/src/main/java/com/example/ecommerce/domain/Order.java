package com.example.ecommerce.domain;

import com.example.ecommerce.strategy.DiscountType;
import com.example.ecommerce.strategy.ShippingType;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record Order(
        String id,
        String customerEmail,
        List<OrderItem> items,
        BigDecimal subtotal,
        DiscountType discountType,
        BigDecimal discountAmount,
        ShippingType shippingType,
        BigDecimal shippingFee,
        BigDecimal total,
        Instant createdAt) {

    public Order {
        items = List.copyOf(items);
    }

    public record OrderItem(String productId, String productName, int quantity, BigDecimal unitPrice,
                            BigDecimal lineTotal) {
    }
}
