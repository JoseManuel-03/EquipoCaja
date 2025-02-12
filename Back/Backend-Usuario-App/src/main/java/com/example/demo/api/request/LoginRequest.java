package com.example.demo.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginRequest {
    @NotBlank private String username;
    @NotBlank private String password;
    // Getters y Setters
}

