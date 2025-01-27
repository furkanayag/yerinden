package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.Market;
import com.yerinden.yerinden.entity.MarketFollower;
import com.yerinden.yerinden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MarketFollowerRepository extends JpaRepository<MarketFollower,Long> {
    Optional<MarketFollower> findByUserAndMarket(User user,Market market);

    List<MarketFollower> findAllByUser(User user);
}
