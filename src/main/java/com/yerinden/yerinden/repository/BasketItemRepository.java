package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.BasketItem;
import com.yerinden.yerinden.entity.Product;
import com.yerinden.yerinden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BasketItemRepository extends JpaRepository<BasketItem,Long> {
    Optional<BasketItem> findBasketItemByUserAndProduct(User user, Product product);
    List<BasketItem> findAllByUser(User user);
}
