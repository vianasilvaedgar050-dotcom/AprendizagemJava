package com.example.ecommerce.dto;

import com.example.ecommerce.strategy.DiscountType;
import com.example.ecommerce.strategy.ShippingType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.util.List;

public record CheckoutRequest(
        @NotBlank @Email @Size(max = 254) String customerEmail,
        @NotEmpty List<@Valid CheckoutItemRequest> items,
        DiscountType discountType,
        ShippingType shippingType) {
}
