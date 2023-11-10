package com.example.springboot.ServiceProviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ServiceProvider getServiceProvider(String id) {
        return serviceProviderRepository.getServiceProvider(id);
    }
}
