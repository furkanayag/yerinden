package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.BasketItem;
import com.yerinden.yerinden.entity.Market;
import com.yerinden.yerinden.entity.Product;
import com.yerinden.yerinden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarketRepository extends JpaRepository<Market,Long> {
    List<Market> findAllByIsActive(Boolean isActive);
}
