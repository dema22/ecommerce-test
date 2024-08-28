package com.example.ecommerce.repository;

import com.example.ecommerce.models.Cart;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;


@Repository
public class CartRepository {
    private final Map<String, Cart> carts = new ConcurrentHashMap<>();

    public Cart save(Cart cart) {
        carts.put(cart.getId(), cart);
        return cart;
    }

    public Optional<Cart> findById(String id) {
        return Optional.ofNullable(carts.get(id));
    }

    public void deleteById(String id) {
        carts.remove(id);
    }

    public List<Cart> findAll() {
        return new ArrayList<>(carts.values());
    }
}
