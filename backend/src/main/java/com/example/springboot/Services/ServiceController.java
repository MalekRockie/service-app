package com.example.springboot.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceController {

    @Autowired
    private ServicesService servicesService;

    @GetMapping("Service/getService/{id}")
    public Services getServiceById(@PathVariable String id)
    {
        return servicesService.getServiceById(id);
    }

    @GetMapping("Service/getServiceByProvider/{id}")
    public Services getServiceByProviderId(@PathVariable String id)
    {
        return servicesService.getServiceByProviderId(id);
    }
}
