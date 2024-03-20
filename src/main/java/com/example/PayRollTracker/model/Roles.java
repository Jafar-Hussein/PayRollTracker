package com.example.PayRollTracker.model;

import org.springframework.security.core.GrantedAuthority;

public enum Roles implements GrantedAuthority {
    ADMIN, USER;

    @Override
    public String getAuthority() {
        return name();
    }
}
