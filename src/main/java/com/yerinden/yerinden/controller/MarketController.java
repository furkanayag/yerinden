package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.AddMarketRequest;
import com.yerinden.yerinden.controller.request.DeleteMarketRequest;
import com.yerinden.yerinden.controller.request.UpdateMarketRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.MarketResponse;
import com.yerinden.yerinden.controller.response.MarketsResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyAdmin;
import com.yerinden.yerinden.security.annotation.AllowAnySeller;
import com.yerinden.yerinden.security.annotation.AllowAnyUser;
import com.yerinden.yerinden.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/market")
public class MarketController {

    private final MarketService service;

    @AllowAnyUser
    @GetMapping
    public ResponseEntity<MarketsResponse> getMarkets(
            @AuthenticationPrincipal UserSession userSession){
        MarketsResponse response = service.findAllActiveMarkets();
        return ResponseEntity.ok(response);
    }

    @AllowAnyUser
    @GetMapping("/{marketId}")
    public ResponseEntity<MarketResponse> getMarketDetail(
            @AuthenticationPrincipal UserSession userSession,
            @PathVariable Long marketId){
        MarketResponse response = service.findMarket(marketId);
        return ResponseEntity.ok(response);
    }

    @AllowAnyAdmin
    @PostMapping
    public ResponseEntity<EmptyResponse> addMarket(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody AddMarketRequest request){
        EmptyResponse response = service.addMarket(request);
        return ResponseEntity.ok(response);
    }

    @AllowAnyAdmin
    @PatchMapping
    public ResponseEntity<EmptyResponse> updateMarket(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody UpdateMarketRequest request){
        EmptyResponse response = service.updateMarket(request);
        return ResponseEntity.ok(response);
    }

    @AllowAnyAdmin
    @DeleteMapping
    public ResponseEntity<EmptyResponse> deleteMarket(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody DeleteMarketRequest request){
        EmptyResponse response = service.deleteMarket(request);
        return ResponseEntity.ok(response);
    }
}
