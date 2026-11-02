package com.example.dlcsp.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public final class CurrentUser {

    private CurrentUser() {}

    /** Returns the user ID from the JWT subject, set by JwtAuthFilter. */
    public static String id() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || auth.getPrincipal() == null) {
            throw new IllegalStateException("No authenticated user");
        }
        return auth.getPrincipal().toString();
    }
}