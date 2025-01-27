package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.BasketItem;
import com.yerinden.yerinden.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
