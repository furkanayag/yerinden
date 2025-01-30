package com.yerinden.yerinden.controller.response;

import com.yerinden.yerinden.entity.Review;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ReviewResponse {
    List<Review> reviews;
}
