package com.example.springboot.ServiceProvider.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("getUser/{id}")
    public User getUser(@PathVariable String id)
    {
        return userService.getUser(id);
    }
}
