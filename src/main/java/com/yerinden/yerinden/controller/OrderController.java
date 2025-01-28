package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
import com.yerinden.yerinden.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class OrderController {

    private final OrderService service;

    @AllowAnyBuyer
    @PostMapping
    public ResponseEntity<EmptyResponse> order(
            @AuthenticationPrincipal UserSession userSession) {
        EmptyResponse response = service.order(userSession);
        return ResponseEntity.ok(response);
    }
}
