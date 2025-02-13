package com.example.demo.api.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ChangePasswordRequest {
    private Long id;
    private String oldPassword;
    @Size(min = 8)
    private String newPassword;
}

