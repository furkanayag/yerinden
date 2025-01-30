package com.yerinden.yerinden.model;

import lombok.Getter;

@Getter
public enum ReviewPoint {
    POINT_1(1D),
    POINT_2(2D),
    POINT_3(3D),
    POINT_4(4D),
    POINT_5(5D)
    ;

    private final Double userPoint;

    ReviewPoint(Double userPoint){
        this.userPoint = userPoint;
    }
}
