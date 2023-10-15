package com.grocery.booking.service.impl;

import com.grocery.booking.utils.constants.ErrorMessage;
import com.grocery.booking.domain.Order;
import com.grocery.booking.domain.Product;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.OrderRequest;
import com.grocery.booking.repository.OrderRepository;
import com.grocery.booking.service.OrderService;
import com.grocery.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserService userService;
    private final OrderRepository orderRepository;
    private final ModelMapper modelMapper;
    private final MailService mailService;

    @Override
    public Order getOrder(Long orderId) {
        User user = userService.getAuthenticatedUser();
        return orderRepository.getByIdAndUserId(orderId, user.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, ErrorMessage.ORDER_NOT_FOUND));
    }

    @Override
    public List<Product> getOrdering() {
        User user = userService.getAuthenticatedUser();
        return user.getProductList();
    }

    @Override
    public List<Order> getUserOrdersList() {
        User user = userService.getAuthenticatedUser();
        return orderRepository.findOrderByUserId(user.getId());
    }

    @Override
    @Transactional
    public Long postOrder(User user, OrderRequest orderRequest) {
        Order order = modelMapper.map(orderRequest, Order.class);
        order.setUser(user);
        order.getProducts().addAll(user.getProductList());
        orderRepository.save(order);
        user.getProductList().clear();
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("order", order);
        mailService.sendMessageHtml(order.getEmail(), "Order #" + order.getId() + "from Abhishek's Grocery Store", attributes);
        return order.getId();
    }
}
