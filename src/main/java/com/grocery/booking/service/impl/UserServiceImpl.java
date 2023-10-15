package com.grocery.booking.service.impl;

import com.grocery.booking.domain.Product;
import com.grocery.booking.repository.ProductRepository;
import com.grocery.booking.utils.constants.ErrorMessage;
import com.grocery.booking.utils.constants.SuccessMessage;
import com.grocery.booking.domain.User;
import com.grocery.booking.dto.ChangePasswordRequest;
import com.grocery.booking.dto.EditUserRequest;
import com.grocery.booking.repository.OrderRepository;
import com.grocery.booking.repository.UserRepository;
import com.grocery.booking.security.UserPrincipal;
import com.grocery.booking.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User getAuthenticatedUser() {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userRepository.findByEmail(principal.getUsername());
    }

    @Override
    @Transactional
    public String editUserInfo(EditUserRequest request) {
        User user = getAuthenticatedUser();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setCity(request.getCity());
        user.setAddress(request.getAddress());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPostIndex(request.getPostIndex());
        return SuccessMessage.USER_UPDATED;
    }

    @Override
    @Transactional
    public String changePassword(ChangePasswordRequest request) {
        if (request.getPassword() != null && !request.getPassword().equals(request.getPassword2())) {
            return ErrorMessage.PASSWORDS_DO_NOT_MATCH;
        }
        User user = getAuthenticatedUser();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        return SuccessMessage.PASSWORD_CHANGED;
    }

    @Override
    public List<Product> getProducts() {
        return productRepository.getAvailableProducts();
    }
}
