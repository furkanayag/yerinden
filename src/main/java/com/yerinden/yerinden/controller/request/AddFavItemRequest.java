package com.yerinden.yerinden.controller.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class AddFavItemRequest {
    private Long productId;
}
