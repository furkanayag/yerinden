package com.yerinden.yerinden.controller.response;

import com.yerinden.yerinden.entity.BuyerTransaction;
import com.yerinden.yerinden.entity.SellerTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class SellerTransactionHistoryResponse {
    List<SellerTransaction> buyerTransactions;
}
