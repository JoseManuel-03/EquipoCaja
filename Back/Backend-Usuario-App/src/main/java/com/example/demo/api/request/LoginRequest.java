package com.example.demo.api.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class LoginRequest {
   private String username;
   private String password;
    // Getters y Setters
}

