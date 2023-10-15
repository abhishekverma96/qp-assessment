package com.grocery.booking.service;

import com.grocery.booking.domain.Product;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.ChangePasswordRequest;
import com.grocery.booking.dto.EditUserRequest;

import java.util.List;

public interface UserService {

    User getAuthenticatedUser();

    String editUserInfo(EditUserRequest request);

    String changePassword(ChangePasswordRequest request);

    List<Product> getProducts();
}
