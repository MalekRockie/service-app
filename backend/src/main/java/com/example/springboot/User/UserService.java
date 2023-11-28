package com.example.springboot.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public User getUser(String id) {
        return userRepository.getUser(id);
    }

    public void SignUp(User newUser) {
        userRepository.SignUp(newUser);
    }

    public void authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public User getUserByUsername(String username) {
        return userRepository.getByUsername(username);
    }
}
