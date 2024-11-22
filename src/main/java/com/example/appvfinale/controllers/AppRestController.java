package com.example.appvfinale.controllers;

import com.example.appvfinale.filter.dtos.AuthenticationRequest;
import com.example.appvfinale.filter.dtos.AuthenticationResponse;
import com.example.appvfinale.filter.dtos.RegisterRequest;
import com.example.appvfinale.entities.User;
import com.example.appvfinale.repository.UserRepository;
import com.example.appvfinale.service.ServiceForController;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AppRestController {
    private final ServiceForController service;
    private final UserRepository repository;
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
           @Valid @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ){
            return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/user")
    public ResponseEntity<User> user(
            @RequestBody AuthenticationResponse token
    ){
        return ResponseEntity.ok(service.gettingUserInfos(token));
    }

    @PostMapping("/validate")
    public ResponseEntity<Boolean> validate(
            @RequestBody AuthenticationResponse response
    ){
        return ResponseEntity.ok(service.validate(response));
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> users(){
        return ResponseEntity.ok(repository.findAll());
    }

}
