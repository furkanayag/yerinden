package com.yerinden.yerinden.controller.request;

import com.yerinden.yerinden.model.ReviewPoint;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DeleteReviewRequest {
    private Long reviewId;
}
