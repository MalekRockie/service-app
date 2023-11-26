package com.example.springboot.ServiceProviders;

import com.example.springboot.User.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("service/GetProviders")
    public List<ServiceProvider> getProviders()
    {
        return serviceProviderService.getProviders();
    }
    @PostMapping("SP/SignUp")
    public ResponseEntity<?> signUp(@RequestBody ServiceProvider newUser)
    {
        serviceProviderService.SignUp(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
