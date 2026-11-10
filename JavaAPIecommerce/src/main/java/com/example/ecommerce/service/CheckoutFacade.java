package com.example.ecommerce.service;

import com.example.ecommerce.domain.Order;
import com.example.ecommerce.domain.Order.OrderItem;
import com.example.ecommerce.domain.Product;
import com.example.ecommerce.dto.CheckoutRequest;
import com.example.ecommerce.dto.CheckoutItemRequest;
import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.strategy.DiscountStrategy;
import com.example.ecommerce.strategy.DiscountType;
import com.example.ecommerce.strategy.ShippingStrategy;
import com.example.ecommerce.strategy.ShippingType;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class CheckoutFacade {

    private final CatalogService catalogService;
    private final OrderRepository orderRepository;
    private final Map<DiscountType, DiscountStrategy> discountStrategies;
    private final Map<ShippingType, ShippingStrategy> shippingStrategies;

    public CheckoutFacade(
            CatalogService catalogService,
            OrderRepository orderRepository,
            List<DiscountStrategy> discountStrategies,
            List<ShippingStrategy> shippingStrategies) {
        this.catalogService = catalogService;
        this.orderRepository = orderRepository;
        this.discountStrategies = discountStrategies.stream()
                .collect(Collectors.toUnmodifiableMap(DiscountStrategy::type, Function.identity()));
        this.shippingStrategies = shippingStrategies.stream()
                .collect(Collectors.toUnmodifiableMap(ShippingStrategy::type, Function.identity()));
    }

    public Order checkout(CheckoutRequest request) {
        DiscountType discountType = request.discountType() == null
                ? DiscountType.NONE : request.discountType();
        ShippingType shippingType = request.shippingType() == null
                ? ShippingType.STANDARD : request.shippingType();

        List<OrderItem> items = request.items().stream()
                .map(this::toOrderItem)
                .toList();
        BigDecimal subtotal = items.stream()
                .map(OrderItem::lineTotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP);

        DiscountStrategy discountStrategy = requiredStrategy(discountStrategies, discountType, "desconto");
        ShippingStrategy shippingStrategy = requiredStrategy(shippingStrategies, shippingType, "frete");
        BigDecimal discountAmount = discountStrategy.calculate(subtotal);
        BigDecimal shippingFee = shippingStrategy.calculateFee();
        BigDecimal total = subtotal.subtract(discountAmount).add(shippingFee)
                .setScale(2, RoundingMode.HALF_UP);

        Order order = new Order(
                UUID.randomUUID().toString(),
                request.customerEmail(),
                items,
                subtotal,
                discountType,
                discountAmount,
                shippingType,
                shippingFee,
                total,
                Instant.now());
        return orderRepository.save(order);
    }

    public Order getOrder(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pedido não encontrado: " + id));
    }

    private OrderItem toOrderItem(CheckoutItemRequest item) {
        Product product = catalogService.getRequired(item.productId());
        BigDecimal lineTotal = product.price().multiply(BigDecimal.valueOf(item.quantity()))
                .setScale(2, RoundingMode.HALF_UP);
        return new OrderItem(product.id(), product.name(), item.quantity(), product.price(), lineTotal);
    }

    private <T, S> S requiredStrategy(Map<T, S> strategies, T type, String description) {
        S strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Estratégia de " + description + " não suportada: " + type);
        }
        return strategy;
    }
}
