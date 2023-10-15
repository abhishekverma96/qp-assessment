package com.grocery.booking.controller;

import com.grocery.booking.domain.Product;
import com.grocery.booking.utils.constants.PathConstants;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.ChangePasswordRequest;
import com.grocery.booking.dto.EditUserRequest;
import com.grocery.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(PathConstants.USER)
public class UserController {

    private final UserService userService;

    @GetMapping("/info")
    public User userInfo() {
        return userService.getAuthenticatedUser();
    }

    @GetMapping("/products")
    public List<Product> getAvailableProducts() {
        return userService.getProducts();
    }

    @PostMapping("/info/edit")
    public String editUserInfo(@Valid EditUserRequest request) {
        return userService.editUserInfo(request);
    }

    @PostMapping("/change/password")
    public String changePassword(@Valid ChangePasswordRequest request) {
        return userService.changePassword(request);
    }
}
