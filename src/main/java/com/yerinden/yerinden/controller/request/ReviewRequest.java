package com.yerinden.yerinden.controller.request;

import com.yerinden.yerinden.model.ReviewPoint;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class ReviewRequest {
    private Long transactionId;
    private Long productId;
    private Long marketId;
    private ReviewPoint productReview;
    private ReviewPoint marketReview;
    private String reviewTextProduct;
    private String reviewTextMarket;
}
