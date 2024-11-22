package com.example.appvfinale.controllers;


import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Configuration
public class ExceptionController {
    @ExceptionHandler({ConstraintViolationException.class})
    public ResponseEntity<Map<String, String>> errorsHandler(ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            errors.put(violation.getPropertyPath().toString(), violation.getMessage());
        }
        return ResponseEntity.ok(errors);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Map<String, String>> errorsHandler(IllegalArgumentException e) {
        Map<String, String> errors = new HashMap<>();
        errors.put("password", "Password can't be null");
        return ResponseEntity.ok(errors);
    }


    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<Boolean> errorsHandler(ExpiredJwtException e){



        return ResponseEntity.ok(false);
    }


    @ExceptionHandler({io.jsonwebtoken.security.SignatureException.class})
    public ResponseEntity<Boolean> errorHandler(io.jsonwebtoken.security.SignatureException e){
        System.out.println(e.getMessage());
        return ResponseEntity.ok(false);
    }

}