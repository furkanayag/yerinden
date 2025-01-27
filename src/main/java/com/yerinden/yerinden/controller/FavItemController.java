package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.BasketAddRequest;
import com.yerinden.yerinden.controller.response.BasketProductsResponse;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.FavProductsResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
import com.yerinden.yerinden.service.BasketService;
import com.yerinden.yerinden.service.FavItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fav")
public class FavItemController {

    private final FavItemService service;

    @AllowAnyBuyer
    @GetMapping("/items")
    public ResponseEntity<FavProductsResponse> getBasketItems(
            @AuthenticationPrincipal UserSession userSession){
        FavProductsResponse response = service.getFavProducts(userSession);
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
