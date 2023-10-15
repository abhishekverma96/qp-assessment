package com.grocery.booking.repository;

import com.grocery.booking.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findAllByOrderByPriceAsc();

    @Query("SELECT product FROM products product " +
            "WHERE  quantity > 0" +
            "ORDER BY product.price ASC")
    List<Product> getAvailableProducts();
}
