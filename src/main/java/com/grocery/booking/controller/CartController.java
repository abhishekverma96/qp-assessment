package com.grocery.booking.controller;

import com.grocery.booking.domain.Product;
import com.grocery.booking.service.CartService;
import com.grocery.booking.utils.constants.PathConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.CART)
public class CartController {

    private final CartService cartService;

    @GetMapping()
    public List<Product> getCart() {
        return cartService.getProdutsInCart();
    }

    @PostMapping("/add")
    public String addProductToCart(@RequestParam("productId") Long productId) {
        return cartService.addProductToCart(productId);
    }

    @PostMapping("/remove")
    public String removeProductFromCart(@RequestParam("productId") Long productId) {
        return cartService.removeProductFromCart(productId);
    }
}
