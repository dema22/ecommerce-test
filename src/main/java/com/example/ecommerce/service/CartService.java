package com.example.ecommerce.service;

import com.example.ecommerce.exception.ResourceNotFoundException;
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

    public Cart addProductsToCart(String id, List<Product> products) throws ResourceNotFoundException {
        return cartRepository.findById(id)
                .map(cart -> {
                    cart.getProducts().addAll(products);
                    cart.setLastUpdated(LocalDateTime.now());
                    return cartRepository.save(cart);
                })
                .orElseThrow(() -> new ResourceNotFoundException("You cant add products to the cart! You have provided an invalid cart id: " + id));
    }

    public Cart getCartById(String id) throws ResourceNotFoundException {
        return cartRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cart with ID " + id + " not found."));
    }

    public void deleteCart(String id) throws ResourceNotFoundException {
        cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart with ID " + id + " not found."));
        cartRepository.deleteById(id);
    }
}
