package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.BasketAddRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.FollowedMarketResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
import com.yerinden.yerinden.service.MarketFollowService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketFollowController {

    private final MarketFollowService service;

    @AllowAnyBuyer
    @GetMapping("/items")
    public ResponseEntity<FollowedMarketResponse> getBasketItems(
            @AuthenticationPrincipal UserSession userSession){
        FollowedMarketResponse response = service.getFollowedMarkets(userSession);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @PostMapping("/add")
    public ResponseEntity<EmptyResponse> addBasketProduct(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody BasketAddRequest request) {
        EmptyResponse response = service.addItem(userSession, request);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @DeleteMapping("/remove")
    public ResponseEntity<EmptyResponse> deleteProduct(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody BasketAddRequest request){
        EmptyResponse response = service.deleteItem(userSession, request);
        return ResponseEntity.ok(response);
    }
}
