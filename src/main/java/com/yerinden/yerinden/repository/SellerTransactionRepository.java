package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.BuyerTransaction;
import com.yerinden.yerinden.entity.Market;
import com.yerinden.yerinden.entity.SellerTransaction;
import com.yerinden.yerinden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SellerTransactionRepository extends JpaRepository<SellerTransaction,Long> {
    List<SellerTransaction> findAllByMarket(Market market);
}
