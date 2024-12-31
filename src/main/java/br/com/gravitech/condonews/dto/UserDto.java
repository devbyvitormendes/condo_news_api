package br.com.gravitech.condonews.dto;

import java.util.UUID;

public record UserDto (UUID id, String username, String email, String password, boolean active, UUID idCondo) {}
