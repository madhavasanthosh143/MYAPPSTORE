package com.example.ecommerce.Appstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.Appstore.DTO.CheckoutRequest;
import com.example.ecommerce.Appstore.model.CartItem;
import com.example.ecommerce.Appstore.service.CartService;

@RestController
@RequestMapping("/cart")
@CrossOrigin(origins = "*")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Long productId,
                            @RequestParam("quantity") int quantity) {
        cartService.addToCart(productId, quantity);
        return "Product added to cart";
    }

    @GetMapping("/view")
    public List<CartItem> viewCart() {
        return cartService.getCartItems();
    }

    @DeleteMapping("/clear")
    public String clearCart() {
        cartService.clearCart();
        return "Cart cleared";
    }
    @PostMapping("/checkout")
    public ResponseEntity<String> placeOrder(@RequestBody CheckoutRequest request) {
        // save order using service (DB, logs, etc.)
        System.out.println("Order received for " + request.getCustomerName());
        
        // You can save customer, address, cart, and payment mode in DB here

        return ResponseEntity.ok("Order placed successfully!");
    }

}
