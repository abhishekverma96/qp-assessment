package com.grocery.booking.controller;

import com.grocery.booking.domain.Order;
import com.grocery.booking.domain.Product;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.ProductRequest;
import com.grocery.booking.service.AdminService;
import com.grocery.booking.utils.constants.PathConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(PathConstants.ADMIN)
@PreAuthorize("hasAuthority('ADMIN')")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/products")
    public List<Product> getProducts() {
        return adminService.getProducts();
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return adminService.getUsers();
    }

    @GetMapping("/order/{orderId}")
    public Order getOrder(@PathVariable Long orderId) {
        return adminService.getOrder(orderId);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return adminService.getOrders();
    }

    @PostMapping("/edit/product")
    public String editProduct(@Valid ProductRequest product) {
        return adminService.editProduct(product);
    }

    @PostMapping("/add/product")
    public String addProduct(@Valid ProductRequest product) {
        return adminService.addProduct(product);
    }
}
