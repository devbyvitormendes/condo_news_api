package br.com.gravitech.condonews.dto.auth;

import java.util.UUID;

public record AuthResponseDto (String token, String refreshToken, long expiresAt, UUID idCondo) {}
