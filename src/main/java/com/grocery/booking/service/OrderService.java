package com.grocery.booking.service;

import com.grocery.booking.domain.Order;
import com.grocery.booking.domain.Product;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.OrderRequest;

import java.util.List;

public interface OrderService {

    Order getOrder(Long orderId);

    List<Product> getOrdering();

    List<Order> getUserOrdersList();

    Long postOrder(User user, OrderRequest orderRequest);
}
