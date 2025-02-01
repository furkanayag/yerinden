package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyAdmin;
import com.yerinden.yerinden.security.annotation.AllowAnySeller;
import com.yerinden.yerinden.security.annotation.AllowAnyUser;
import com.yerinden.yerinden.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private ProductService service;

    @AllowAnyUser
    @GetMapping
    public ResponseEntity<EmptyResponse> getProducts(
            @AuthenticationPrincipal UserSession userSession){
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnyUser
    @GetMapping("/{marketId}")
    public ResponseEntity<EmptyResponse> getMarketProducts(
            @AuthenticationPrincipal UserSession userSession,
            @PathVariable String marketId){
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnyUser
    @GetMapping("/{productId}")
    public ResponseEntity<EmptyResponse> getProductDetail(
            @AuthenticationPrincipal UserSession userSession,
            @PathVariable Long productId){
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnySeller
    @PostMapping
    public ResponseEntity<EmptyResponse> addProduct(
            @AuthenticationPrincipal UserSession userSession){
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnySeller
    @PatchMapping
    public ResponseEntity<EmptyResponse> updateProduct(
            @AuthenticationPrincipal UserSession userSession){
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnySeller
    @DeleteMapping
    public ResponseEntity<EmptyResponse> deleteProduct(
            @AuthenticationPrincipal UserSession userSession){
        return ResponseEntity.ok(new EmptyResponse());
    }


}
