package com.example.ecommerce.repository;

import com.example.ecommerce.domain.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;

@Repository
public class ProductRepository {

    private final Map<String, Product> products = new ConcurrentHashMap<>();

    public ProductRepository() {
        products.put("BOOK-001", new Product("BOOK-001", "Livro de padrões de projeto",
                new BigDecimal("25.00")));
        products.put("HEAD-001", new Product("HEAD-001", "Fone de ouvido",
                new BigDecimal("75.00")));
    }

    public List<Product> findAll() {
        return products.values().stream()
                .sorted((first, second) -> first.id().compareTo(second.id()))
                .toList();
    }

    public Optional<Product> findById(String id) {
        return Optional.ofNullable(products.get(id));
    }
}
