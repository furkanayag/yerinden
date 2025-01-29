package com.yerinden.yerinden.controller.request;

import com.yerinden.yerinden.model.OrderStatus;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UpdateOrderStatusObject {
    private Long sellerTransactionId;
    private OrderStatus orderStatus;
}
