package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.AddFavItemRequest;
import com.yerinden.yerinden.controller.request.DeleteFavItemRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.FavProductsResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
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
    public ResponseEntity<FavProductsResponse> getFavItems(
            @AuthenticationPrincipal UserSession userSession){
        FavProductsResponse response = service.getFavProducts(userSession);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @PostMapping("/add")
    public ResponseEntity<EmptyResponse> addFavItem(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody AddFavItemRequest request) {
        EmptyResponse response = service.addItem(userSession, request);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @DeleteMapping("/remove")
    public ResponseEntity<EmptyResponse> deleteFavItem(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody DeleteFavItemRequest request){
        EmptyResponse response = service.deleteItem(userSession, request);
        return ResponseEntity.ok(response);
    }
}
