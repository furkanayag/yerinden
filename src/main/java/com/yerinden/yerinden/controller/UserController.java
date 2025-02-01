package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.UpdateUserRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
import com.yerinden.yerinden.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @AllowAnyBuyer
    @PatchMapping
    public ResponseEntity<EmptyResponse> updateUser(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody UpdateUserRequest request){
        EmptyResponse response = service.updateUser(userSession, request);
        return ResponseEntity.ok(response);
    }
}
