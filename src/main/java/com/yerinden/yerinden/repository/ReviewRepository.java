package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    List<Review> findAllByUser(User user);
    List<Review> findAllByProduct(Product product);
    List<Review> findAllByMarket(Market market);
    Optional<Review> findByUserAndMarket(User user, Market market);
    Optional<Review> findByUserAndProduct(User user, Product product);
}
