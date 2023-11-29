package com.example.springboot.User;

import com.example.springboot.ServiceProviders.CustomServiceProviderDetails;
import com.example.springboot.ServiceProviders.ServiceProvider;
import com.example.springboot.ServiceProviders.ServiceProviderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.getByUsername(username);
        ServiceProvider serviceProvider = null;

        if (user == null) {
            serviceProvider = serviceProviderRepository.getByUsername(username);
        }

        if (user == null && serviceProvider == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user != null ? new CustomUserDetails(user) : new CustomServiceProviderDetails(serviceProvider);
    }
}
