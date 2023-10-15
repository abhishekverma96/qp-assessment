package com.grocery.booking.controller;

import com.grocery.booking.domain.Order;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.OrderRequest;
import com.grocery.booking.service.OrderService;
import com.grocery.booking.service.UserService;
import com.grocery.booking.utils.constants.PathConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

import static com.grocery.booking.utils.constants.SuccessMessage.ORDER_PLACED;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.ORDER)
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @GetMapping("/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return orderService.getOrder(orderId);
    }

    @GetMapping("/user/orders")
    public List<Order> getUserOrdersList() {
        return orderService.getUserOrdersList();
    }

    @PostMapping
    public String postOrder(@Valid OrderRequest orderRequest) {
        User user = userService.getAuthenticatedUser();
        Long orderId = orderService.postOrder(user, orderRequest);
        return ORDER_PLACED + orderId;
    }
}
