package br.com.gravitech.condonews.dto.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequestDto(
        @NotBlank(message = "Username is required")
        String username,
        @NotBlank(message = "Password is required")
        String password
) {}