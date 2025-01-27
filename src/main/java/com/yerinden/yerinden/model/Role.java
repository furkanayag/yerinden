package com.yerinden.yerinden.model;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;

@Getter
public enum Role implements GrantedAuthority {
    ADMIN,
    SELLER,
    BUYER;

    @Override
    public String getAuthority() {
        return name();
    }
}
