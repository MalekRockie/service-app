package com.example.springboot.ServiceProviders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiceProviderService {
    @Autowired
    private ServiceProviderRepository serviceProviderRepository;

    public ServiceProvider getServiceProvider(String id) {
        return serviceProviderRepository.getServiceProvider(id);
    }

    public List<ServiceProvider> getProviders() {
        return serviceProviderRepository.getProviders();
    }

    public void SignUp(ServiceProvider newUser) {
        serviceProviderRepository.SignUp(newUser);
    }
}
