package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.UpdateOrderStatusRequest;
import com.yerinden.yerinden.controller.response.BuyerTransactionHistoryResponse;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.SellerTransactionHistoryResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
import com.yerinden.yerinden.security.annotation.AllowAnySeller;
import com.yerinden.yerinden.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @AllowAnyBuyer
    @GetMapping
    public ResponseEntity<BuyerTransactionHistoryResponse> getBuyerOrders(
            @AuthenticationPrincipal UserSession userSession) {
        BuyerTransactionHistoryResponse response = service.getBuyerOrders(userSession);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @PostMapping
    public ResponseEntity<EmptyResponse> order(
            @AuthenticationPrincipal UserSession userSession) {
        EmptyResponse response = service.order(userSession);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @PostMapping
    public ResponseEntity<EmptyResponse> review(
            @AuthenticationPrincipal UserSession userSession) {
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnySeller
    @GetMapping("/seller")
    public ResponseEntity<SellerTransactionHistoryResponse> getSellerOrders(
            @AuthenticationPrincipal UserSession userSession) {
        SellerTransactionHistoryResponse response = service.getSellerOrders(userSession);
        return ResponseEntity.ok(response);
    }

    @AllowAnySeller
    @PatchMapping("/seller")
    public ResponseEntity<EmptyResponse> updateOrderStatus(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody UpdateOrderStatusRequest request) {
        EmptyResponse response = service.updateOrderStatus(request);
        return ResponseEntity.ok(response);
    }
}
