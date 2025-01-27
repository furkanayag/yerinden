package com.yerinden.yerinden.controller.request;

import com.yerinden.yerinden.model.Role;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class BasketAddRequest {
    private Long productId;
}
