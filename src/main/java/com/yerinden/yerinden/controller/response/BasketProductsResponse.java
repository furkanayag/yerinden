package com.yerinden.yerinden.controller.response;

import com.yerinden.yerinden.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BasketProductsResponse {
    List<Product> products;
}
