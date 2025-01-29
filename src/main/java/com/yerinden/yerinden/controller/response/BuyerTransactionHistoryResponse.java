package com.yerinden.yerinden.controller.response;

import com.yerinden.yerinden.entity.BuyerTransaction;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BuyerTransactionHistoryResponse {
    List<BuyerTransaction> buyerTransactions;
}
