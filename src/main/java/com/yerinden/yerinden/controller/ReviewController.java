package com.yerinden.yerinden.controller;

import com.yerinden.yerinden.controller.request.DeleteReviewRequest;
import com.yerinden.yerinden.controller.request.ReviewRequest;
import com.yerinden.yerinden.controller.response.EmptyResponse;
import com.yerinden.yerinden.controller.response.ReviewResponse;
import com.yerinden.yerinden.model.ReviewType;
import com.yerinden.yerinden.security.UserSession;
import com.yerinden.yerinden.security.annotation.AllowAnyBuyer;
import com.yerinden.yerinden.security.annotation.AllowAnyUser;
import com.yerinden.yerinden.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/review")
public class ReviewController {

    private final ReviewService service;

    @AllowAnyUser
    @GetMapping
    public ResponseEntity<ReviewResponse> getReviews(
            @AuthenticationPrincipal UserSession userSession,
            @RequestParam ReviewType reviewType,
            @RequestParam Long id){
        ReviewResponse response = service.getReviews(userSession, reviewType, id);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @PostMapping
    public ResponseEntity<EmptyResponse> review(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody ReviewRequest request) {
        EmptyResponse response = service.review(userSession, request);
        return ResponseEntity.ok(response);
    }

    @AllowAnyBuyer
    @DeleteMapping
    public ResponseEntity<EmptyResponse> deleteReview(
            @AuthenticationPrincipal UserSession userSession,
            @RequestBody DeleteReviewRequest request) {
        EmptyResponse response = service.delete(userSession, request);
        return ResponseEntity.ok(response);
    }
}
