package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.AddBasketRequest;
import com.yerinden.yerinden.controller.response.BasketProductsResponse;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
import com.yerinden.yerinden.service.BasketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/basket")
public class BasketController {

    private final BasketService service;

    @AllowAnyBuyer
    @GetMapping("/items")
    public ResponseEntity<BasketProductsResponse> getBasketItems(
            @AuthenticationPrincipal UserSession userSession){
        BasketProductsResponse response = service.getBasketProducts(userSession);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @PostMapping("/add")
    public ResponseEntity<EmptyResponse> addBasketProduct(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody AddBasketRequest request) {
        EmptyResponse response = service.addItem(userSession, request);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @DeleteMapping("/remove")
    public ResponseEntity<EmptyResponse> deleteProduct(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody AddBasketRequest request){
        EmptyResponse response = service.deleteItem(userSession, request);
        return ResponseEntity.ok(response);
    }
}
