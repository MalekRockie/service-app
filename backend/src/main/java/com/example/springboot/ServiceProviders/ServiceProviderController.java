package com.example.springboot.ServiceProviders;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceProviderController {

    private ServiceProviderService serviceProviderService;

    public ServiceProviderController(ServiceProviderService serviceProviderService) {
        this.serviceProviderService = serviceProviderService;
    }
    @GetMapping("service/GetProvider/{id}")
    public ServiceProvider getServiceProvider(@PathVariable String id)
    {
        return serviceProviderService.getServiceProvider(id);
    }
}
