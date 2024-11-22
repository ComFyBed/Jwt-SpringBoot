package com.example.appvfinale.service;

import com.example.appvfinale.filter.dtos.AuthenticationRequest;
import com.example.appvfinale.filter.dtos.AuthenticationResponse;
import com.example.appvfinale.filter.dtos.RegisterRequest;
import com.example.appvfinale.entities.Roles;
import com.example.appvfinale.entities.User;
import com.example.appvfinale.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ServiceForController {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final ServiceJwt serviceJwt;

    private final UserDetailsService userDetailsService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request){
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .age(request.getAge())
                .email(request.getEmail())
                .gender(request.getGender())
                .role(Roles.USER)
                .password(passwordEncoder.encode(request.getPassword()))
                .build();
        repository.save(user);
        return AuthenticationResponse.builder()
                .token(serviceJwt.generateToken(user)).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        var user = repository.findByEmail(request.getEmail()).orElseThrow();

        return AuthenticationResponse.builder()
                .token(serviceJwt.generateToken(user))
                .build();
    }

    public User gettingUserInfos(AuthenticationResponse response){
        String username = serviceJwt.extractUsername(response.getToken());
        return repository.findByEmail(username).orElseThrow();
    }

    public Boolean validate(AuthenticationResponse response){
        UserDetails userDetails = userDetailsService.loadUserByUsername(
                serviceJwt.extractUsername(response.getToken())
        );
        return serviceJwt.isTokenValid(response.getToken(), userDetails);
    }
}
