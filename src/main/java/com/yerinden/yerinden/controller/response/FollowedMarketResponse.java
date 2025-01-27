package com.yerinden.yerinden.controller.response;

import com.yerinden.yerinden.entity.Market;
import com.yerinden.yerinden.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FollowedMarketResponse {
    List<Market> markets;
}
