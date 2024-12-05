package com.aidar.prodcat.controllers;

import com.aidar.prodcat.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private UserService userService;


//    @PostMapping("/register")
//    public ResponseEntity<UserResponseDTO> registerUser(
//            @Valid @RequestBody UserRequestDTO userDtoRequest
//    ) {
//        UserResponseDTO registeredUser = userService.registerNewUser(userDtoRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
//    }
//
//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody UserRequestDTO loginRequest) {
//        try {
//            UserResponseDTO userResponse = userService.loginUser(loginRequest);
//            return ResponseEntity.ok(userResponse);
//        } catch (BadCredentialsException e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
//                    .body(Map.of("message", e.getMessage()));
//        }
//    }
}