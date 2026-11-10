package com.example.springedgar;

import edgartaskmanager.transaction.TransactionRequest;
import edgartaskmanager.transaction.TransactionService;
import edgartaskmanager.transaction.TransactionType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(
        classes = edgartaskmanager.SpringEdgarApplication.class,
        properties = "spring.ai.openai.api-key=test-key")
class SpringEdgarApplicationTests {

    @Autowired
    private TransactionService transactionService;

    @Test
    void contextLoads() {
    }

    @Test
    void persistsAndFiltersTransactionsByCategory() {
        var saved = transactionService.create(new TransactionRequest(
                "Almoço", new BigDecimal("42.50"), "Alimentação", TransactionType.EXPENSE));

        assertNotNull(saved.getId());
        assertEquals(1, transactionService.findByCategory("alimentação").size());
        assertEquals(0, transactionService.findByCategory("transporte").size());
    }

}
