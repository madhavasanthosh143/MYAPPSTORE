package com.example.ecommerce.Appstore.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.Appstore.model.CartItem;
import com.example.ecommerce.Appstore.model.Product;
import com.example.ecommerce.Appstore.repository.CartItemRepository;
import com.example.ecommerce.Appstore.repository.ProductRepository;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToCart(Long productId, int quantity) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            CartItem item = new CartItem();
            item.setProduct(product);
            item.setQuantity(quantity);
            item.setTotalPrice(quantity * product.getPrice());

            cartItemRepository.save(item);
        } else {
            throw new RuntimeException("Product not found with ID: " + productId);
        }
    }

    public List<CartItem> getCartItems() {
        return cartItemRepository.findAll();
    }

    public void clearCart() {
        cartItemRepository.deleteAll();
    }
}
