package com.grocery.booking.repository;

import com.grocery.booking.domain.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @EntityGraph(attributePaths = {"products", "user", "user.roles"})
    List<Order> findAll();

    @EntityGraph(attributePaths = {"products", "user", "user.roles"})
    Optional<Order> getById(Long orderId);

    @EntityGraph(attributePaths = {"products"})
    Optional<Order> getByIdAndUserId(Long orderId, Long userId);

    @EntityGraph(attributePaths = {"products", "user", "user.roles"})
    List<Order> findOrderByUserId(Long userId);
}
