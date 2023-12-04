package com.example.springboot.ServiceProviders;

import com.example.springboot.User.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    @GetMapping("/currentSP")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ServiceProvider> getCurrentSP(Principal principal) {
        // Get the currently authenticated user's username
        String username = principal.getName();

        // Fetch the user's details from the database using the username
        ServiceProvider serviceProvider = serviceProviderService.getByUsername(username);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(serviceProvider, headers, HttpStatus.OK);
    }
}
