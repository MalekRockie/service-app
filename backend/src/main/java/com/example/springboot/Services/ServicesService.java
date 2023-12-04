package com.example.springboot.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServicesService {
    @Autowired
    private ServicesRepository servicesRepository;

    public Services getServiceById(String id) {
        return servicesRepository.getServiceById(id);
    }

    public Services getServiceByProviderId(String id) {
        return servicesRepository.getServiceByProviderId(id);
    }
}
