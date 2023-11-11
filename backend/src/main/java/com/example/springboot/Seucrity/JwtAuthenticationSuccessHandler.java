package com.example.springboot.Seucrity;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;
import jakarta.servlet.ServletException;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;


public class JwtAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

    private final JWTTokenUtils jwtTokenUtils;

    public JwtAuthenticationSuccessHandler(JWTTokenUtils jwtTokenUtils) {
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String username = authentication.getName();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String token = jwtTokenUtils.generateToken(username, roles);
        response.addHeader("Authorization", "Bearer " + token);
    }
}

