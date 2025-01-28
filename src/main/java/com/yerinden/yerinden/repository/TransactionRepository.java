package com.yerinden.yerinden.repository;

import com.yerinden.yerinden.entity.FavItem;
import com.yerinden.yerinden.entity.Product;
import com.yerinden.yerinden.entity.Transaction;
import com.yerinden.yerinden.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {
}
