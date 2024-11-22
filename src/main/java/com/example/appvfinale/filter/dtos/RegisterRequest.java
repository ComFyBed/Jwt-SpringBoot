package com.example.appvfinale.filter.dtos;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String password;
    private Integer age;
    private String email;
    private String gender;
}
