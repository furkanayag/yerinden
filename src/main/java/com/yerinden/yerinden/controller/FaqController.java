package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnySeller;
import com.yerinden.yerinden.security.annotation.AllowAnyUser;
import com.yerinden.yerinden.service.MarketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/faq")
public class FaqController {

    @AllowAnyUser
    @GetMapping
    public ResponseEntity<EmptyResponse> getFaqs(
            @AuthenticationPrincipal UserSession userSession){
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnySeller
    @PostMapping
    public ResponseEntity<EmptyResponse> addFaq(
            @AuthenticationPrincipal UserSession userSession){
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnySeller
    @PatchMapping
    public ResponseEntity<EmptyResponse> updateFaq(
            @AuthenticationPrincipal UserSession userSession){
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnySeller
    @PatchMapping
    public ResponseEntity<EmptyResponse> deleteFaq(
            @AuthenticationPrincipal UserSession userSession){
        return ResponseEntity.ok(new EmptyResponse());
    }
}
