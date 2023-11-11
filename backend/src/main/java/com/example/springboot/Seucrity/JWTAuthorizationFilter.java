package com.example.springboot.Seucrity;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
    private JWTTokenUtils jwtTokenUtils;

//    private static final Logger logger = LoggerFactory.getLogger(JWTAuthorizationFilter.class);

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTTokenUtils jwtTokenUtils) {
        super(authenticationManager);
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String path = request.getRequestURI();
        if ("/login".equals(path)) {
            chain.doFilter(request, response);
            return;
        }

//        logger.info("Processing request to authenticate user");

        String header = request.getHeader("Authorization");

        if (header == null || !header.startsWith("Bearer ")) {
//            logger.warn("No Authorization header or invalid format");
            chain.doFilter(request, response);
            return;
        }

        String token = header.replace("Bearer ", "");
        String username = jwtTokenUtils.validateToken(token);
        if (username != null) {
//            logger.info("User authenticated: {}", username);

            List<String> roles = jwtTokenUtils.getRolesFromToken(token);
            List<GrantedAuthority> authorities = roles.stream()
                    .map(role -> new SimpleGrantedAuthority(role))
                    .collect(Collectors.toList());

            // log roles b4 setting sec context
//            logger.info("Roles from token: {}", roles);


            UserDetails userDetails = User.withUsername(username).password("").authorities(authorities).build();
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Log roles after setting Security Context!!
//            logger.info("Roles after setting SecurityContextHolder: {}", SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        }
//        else {
//            logger.warn("Invalid token");
//        }

        chain.doFilter(request, response);
    }
}
