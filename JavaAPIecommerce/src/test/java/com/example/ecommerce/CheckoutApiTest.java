package com.example.ecommerce;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CheckoutApiTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void checkoutAppliesDiscountAndShippingStrategies() throws Exception {
        mockMvc.perform(post("/api/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "customerEmail": "cliente@example.com",
                                  "items": [{"productId": "HEAD-001", "quantity": 2}],
                                  "discountType": "PERCENTAGE",
                                  "shippingType": "STANDARD"
                                }
                                """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.subtotal", is(150.00)))
                .andExpect(jsonPath("$.discountAmount", is(15.00)))
                .andExpect(jsonPath("$.shippingFee", is(5.00)))
                .andExpect(jsonPath("$.total", is(140.00)));
    }

    @Test
    void returnsNotFoundForUnknownProduct() throws Exception {
        mockMvc.perform(post("/api/checkout")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "customerEmail": "cliente@example.com",
                                  "items": [{"productId": "UNKNOWN", "quantity": 1}]
                                }
                                """))
                .andExpect(status().isNotFound());
    }

    @Test
    void listsCatalogProducts() throws Exception {
        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()", is(2)));
    }
}
