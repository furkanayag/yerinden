package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.LoginRequest;
import com.yerinden.yerinden.controller.request.SignUpRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.LoginResponse;
import com.yerinden.yerinden.security.annotation.AllowAnyUser;
import com.yerinden.yerinden.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    @GetMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        LoginResponse response = service.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<EmptyResponse> signUp(@RequestBody SignUpRequest request){
        EmptyResponse response = service.signUp(request);
        return ResponseEntity.ok(response);
    }

    @AllowAnyUser
    @PatchMapping("/verify-email")
    public ResponseEntity<EmptyResponse> verifyEmail(){
        //todo implement email verification
        return ResponseEntity.ok(new EmptyResponse());
    }

    @AllowAnyUser
    @PostMapping("/forgot-password")
    public ResponseEntity<EmptyResponse> forgotPassword(){
        //todo forgot password flow
        return ResponseEntity.ok(new EmptyResponse());
    }
}
