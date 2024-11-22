package com.example.appvfinale.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
@Entity
@Table(name = "UserTable")

public class User implements UserDetails {
    @Id
    @GeneratedValue
    private Integer id;
    @NotEmpty(message = "Empty firstname")
    private String firstname;
    @NotEmpty(message = "Empty lastname")
    private String lastname;
    @NotEmpty(message = "Empty email")
    @Email(message = "Enter a valid email")
    private String email;
    @NotEmpty(message = "Empty password")
    private String password;
    @NotEmpty(message = "Empty gender")
    private String gender;
    @NotNull(message = "Null value for age")
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Roles role;




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
