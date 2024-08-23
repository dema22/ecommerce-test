package com.example.ecommerce.service;

import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CartService {
    private final CartRepository cartRepository;

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    // Create a new cart
    public Cart createCart() {
        Cart cart = new Cart();
        return cartRepository.save(cart);
    }

    public Cart addProductsToCart(String id, List<Product> products) {
        Cart cart = cartRepository.findById(id);
        if (cart != null) {
            cart.getProducts().addAll(products);
            cart.setLastUpdated(LocalDateTime.now());
            return cartRepository.save(cart);
        }
        return null;
    }

    public Cart getCartById(String id) {
        return cartRepository.findById(id);
    }

    public boolean deleteCart(String id) {
        if (cartRepository.findById(id) != null) {
            cartRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
