package com.yerinden.yerinden.controller.request;

import com.yerinden.yerinden.model.Role;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginRequest {
    private String email;
    private String password;
    private Role platform;
}
