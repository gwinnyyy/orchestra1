package com.hps.orchestraspa.controller;

import com.hps.orchestraspa.model.User;
import com.hps.orchestraspa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.hps.orchestraspa.model.LoginResponse;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) throws Exception {
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(userService.get(id));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody User loginCredentials) {
    try {
        // Pull the email and password out of the JSON body
        User user = userService.login(loginCredentials.getUserEmail(), loginCredentials.getUserPassword());
        
        // Build the successful response
        LoginResponse response = new LoginResponse(
            user.getUserId(),
            user.getUserName(),
            user.getUserRole(),
            "Welcome back, " + user.getUserName() + "!",
            true
        );
        
        return ResponseEntity.ok(response);
        
    } catch (Exception e) {
        // Build the failure response
        LoginResponse errorResponse = new LoginResponse();
        errorResponse.setMessage("Invalid email or password.");
        errorResponse.setSuccess(false);
        
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
    }
}
}
