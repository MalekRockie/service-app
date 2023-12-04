package com.example.springboot.ServiceProviders;

import com.example.springboot.User.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomServiceProviderDetails implements UserDetails {

    private ServiceProvider serviceProvider;
    public CustomServiceProviderDetails(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return serviceProvider.getPassword();
    }

    public String getUsername() {
        return serviceProvider.getEmailAddress();
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

    public ServiceProvider getUser() {
        return serviceProvider;
    }
}
