package com.yerinden.yerinden.controller.request;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class SignUpRequest {
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean isKKKApproved;
    private Boolean isCommercialsApproved;
}
