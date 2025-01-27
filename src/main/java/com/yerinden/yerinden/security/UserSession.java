package com.yerinden.yerinden.security;

import com.yerinden.yerinden.model.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Data
public class UserSession implements UserDetails {

    private Long userId;
    private String name;
    private String surname;
    private String email;
    private String phoneNumber;
    private Role role;
    private Set<Role> roles;

    public UserSession(Long userId, String name, String surname, String email, String phoneNumber, Role role, Set<Role> roles) {
        this.userId = userId;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
