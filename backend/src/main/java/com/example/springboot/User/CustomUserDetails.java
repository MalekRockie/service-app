package com.example.springboot.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomUserDetails implements UserDetails {

    private User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // For now, let's not worry about roles and authorities.
        // We can populate this later based on user roles.
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    public String getUsername() {
        return user.getEmail_address();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Assuming accounts never expire
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Assuming accounts are never locked
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Credentials (like passwords) never expire
        return true;
    }

    @Override
    public boolean isEnabled() {
        // You can decide this based on a user property, e.g., an "enabled" field in the user model.
        // For now, assuming all users are enabled:
        return true;
    }

    public User getUser() {
        return user;
    }
}