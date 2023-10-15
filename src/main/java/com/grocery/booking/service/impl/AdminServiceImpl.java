package com.grocery.booking.service.impl;

import com.grocery.booking.utils.constants.ErrorMessage;
import com.grocery.booking.utils.constants.SuccessMessage;
import com.grocery.booking.domain.Order;
import com.grocery.booking.domain.Product;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.ProductRequest;
import com.grocery.booking.repository.OrderRepository;
import com.grocery.booking.repository.ProductRepository;
import com.grocery.booking.repository.UserRepository;
import com.grocery.booking.service.AdminService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Product> getProducts() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public Order getOrder(Long orderId) {
        return orderRepository.getById(orderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<Order> getOrders() {
        return orderRepository.findAll();

    }

    @Override
    public Product getProductById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.PRODUCT_NOT_FOUND));
    }

    @Override
    @SneakyThrows
    @Transactional
    public String editProduct(ProductRequest productRequest) {
        return saveProduct(productRequest, SuccessMessage.PRODUCT_EDITED);
    }

    @Override
    @SneakyThrows
    @Transactional
    public String addProduct(ProductRequest productRequest) {
        return saveProduct(productRequest, SuccessMessage.PRODUCT_ADDED);
    }

    private String saveProduct(ProductRequest productRequest, String message) {
        Product product = modelMapper.map(productRequest, Product.class);
        productRepository.save(product);
        return message;
    }
}
