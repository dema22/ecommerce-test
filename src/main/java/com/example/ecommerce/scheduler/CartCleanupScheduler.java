package com.example.ecommerce.scheduler;

import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CartCleanupScheduler {
    @Autowired
    private CartService cartService;

    // Runs every 1 minute to check for inactive carts
    @Scheduled(fixedRate = 60000)
    public void cleanUpInactiveCarts() {
        System.out.println("Running scheduled cleanup of inactive carts");
        cartService.deleteInactiveCarts();
    }
}
