package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.BasketItem;
import com.yerinden.yerinden.entity.FavItem;
import com.yerinden.yerinden.entity.Product;
import com.yerinden.yerinden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FavItemRepository extends JpaRepository<FavItem,Long> {
    Optional<FavItem> findByUserAndProduct(User user, Product product);
    List<FavItem> findAllByUser(User user);
}
