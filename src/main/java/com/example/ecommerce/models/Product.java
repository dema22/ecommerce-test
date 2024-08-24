package com.example.ecommerce.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @NotNull(message = "Product ID cannot be null")
    private int id;
    @NotBlank(message = "Description cannot be blank")
    private String description;
    @Min(value = 0, message = "Amount must be positive")
    private double amount;
}
