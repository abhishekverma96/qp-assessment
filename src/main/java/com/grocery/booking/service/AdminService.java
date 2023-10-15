package com.grocery.booking.service;

import com.grocery.booking.domain.Order;
import com.grocery.booking.domain.Product;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.ProductRequest;

import java.util.List;

public interface AdminService {

    List<Product> getProducts();

    List<User> getUsers();

    Order getOrder(Long orderId);

    List<Order> getOrders();

    Product getProductById(Long productId);

    String editProduct(ProductRequest productRequest);

    String addProduct(ProductRequest productRequest);
}
