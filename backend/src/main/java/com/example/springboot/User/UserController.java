package com.example.springboot.User;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
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

    @PostMapping("User/SignUp")
    public ResponseEntity<?> signUp(@RequestBody User newUser)
    {
        userService.SignUp(newUser);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
