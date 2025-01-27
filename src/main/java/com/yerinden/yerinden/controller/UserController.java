package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.LoginRequest;
import com.yerinden.yerinden.controller.request.SignUpRequest;
import com.yerinden.yerinden.controller.request.UserUpdateRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.LoginResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
import com.yerinden.yerinden.service.AuthService;
import com.yerinden.yerinden.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @AllowAnyBuyer
    @PatchMapping
    public ResponseEntity<EmptyResponse> updateUser(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody UserUpdateRequest request){
        EmptyResponse response = service.updateUser(userSession, request);
        return ResponseEntity.ok(response);
    }
}
