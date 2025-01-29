package com.yerinden.yerinden.controller.request;

import com.yerinden.yerinden.model.OrderStatus;
import lombok.Data;
import lombok.Getter;

import java.util.List;

@Data
@Getter
public class UpdateOrderStatusRequest {
    List<UpdateOrderStatusObject> updateOrderStatusObjects;
}
