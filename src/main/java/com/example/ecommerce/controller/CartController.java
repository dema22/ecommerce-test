package com.example.ecommerce.controller;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
public class CartController {

    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping
    public ResponseEntity<Cart> createCart() {
        Cart newCart = cartService.createCart();
        return ResponseEntity.status(HttpStatus.CREATED).body(newCart);
    }

    // Add products to a cart by ID
    @PostMapping("/{id}/products")
    public ResponseEntity<Cart> addProducts(@PathVariable String id,@RequestBody List<Product> products) throws ResourceNotFoundException {
        Cart updatedCart = cartService.addProductsToCart(id, products);
        if (updatedCart != null) {
            return ResponseEntity.ok(updatedCart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Get cart details by ID
    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable String id) throws ResourceNotFoundException {
        Cart cart = cartService.getCartById(id);
        if (cart != null) {
            return ResponseEntity.ok(cart);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) throws ResourceNotFoundException {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

}
