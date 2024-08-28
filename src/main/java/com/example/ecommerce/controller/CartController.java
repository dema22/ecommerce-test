package com.example.ecommerce.controller;

import com.example.ecommerce.exception.ResourceNotFoundException;
import com.example.ecommerce.models.Cart;
import com.example.ecommerce.models.Product;
import com.example.ecommerce.service.CartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carts")
@Validated
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


    @PostMapping("/{id}/products")
    public ResponseEntity<Cart> addProducts(@PathVariable String id, @Valid @RequestBody List<@Valid Product> products) throws ResourceNotFoundException {
        Cart updatedCart = cartService.addProductsToCart(id, products);
        return ResponseEntity.ok(updatedCart);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable String id) throws ResourceNotFoundException {
        Cart cart = cartService.getCartById(id);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable String id) throws ResourceNotFoundException {
        cartService.deleteCart(id);
        return ResponseEntity.noContent().build();
    }

}
