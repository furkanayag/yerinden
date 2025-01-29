package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.BuyerTransaction;
import com.yerinden.yerinden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuyerTransactionRepository extends JpaRepository<BuyerTransaction,Long> {
    List<BuyerTransaction> findAllByUser(User user);
}
