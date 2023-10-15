package com.grocery.booking.service;

import com.grocery.booking.domain.Product;

import java.util.List;

public interface CartService {

    List<Product> getProdutsInCart();

    String addProductToCart(Long productId);

    String removeProductFromCart(Long productId);
}
