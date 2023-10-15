package com.grocery.booking.service.impl;

import com.grocery.booking.domain.Product;
import com.grocery.booking.domain.User;
import com.grocery.booking.repository.ProductRepository;
import com.grocery.booking.service.CartService;
import com.grocery.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.grocery.booking.utils.constants.SuccessMessage.PRODUCT_ADDED;
import static com.grocery.booking.utils.constants.SuccessMessage.PRODUCT_REMOVED;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final UserService userService;
    private final ProductRepository productRepository;

    @Override
    public List<Product> getProdutsInCart() {
        User user = userService.getAuthenticatedUser();
        return user.getProductList();
    }

    @Override
    @Transactional
    public String addProductToCart(Long productId) {
        User user = userService.getAuthenticatedUser();
        Product product = productRepository.getOne(productId);
        user.getProductList().add(product);
        return PRODUCT_ADDED;
    }

    @Override
    @Transactional
    public String removeProductFromCart(Long productId) {
        User user = userService.getAuthenticatedUser();
        Product product = productRepository.getOne(productId);
        user.getProductList().remove(product);
        return PRODUCT_REMOVED;
    }
}
