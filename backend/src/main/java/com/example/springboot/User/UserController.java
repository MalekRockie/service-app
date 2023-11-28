package com.example.springboot.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

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
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String username = credentials.get("username");
        String password = credentials.get("password");
        userService.authenticate(username, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/currentUser")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<User> getCurrentUser(Principal principal) {
        // Get the currently authenticated user's username
        String username = principal.getName();

        // Fetch the user's details from the database using the username
        User user = userService.getUserByUsername(username);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<>(user, headers, HttpStatus.OK);
    }


}
