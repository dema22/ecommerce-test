package com.example.ecommerce.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    private String id = UUID.randomUUID().toString();
    private List<Product> products = new ArrayList<>();
    private LocalDateTime lastUpdated;
}