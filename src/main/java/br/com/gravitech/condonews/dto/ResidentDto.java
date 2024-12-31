package br.com.gravitech.condonews.dto;

import java.util.UUID;

public record ResidentDto (UUID id, String name, String email, String cpf, String phone, String apartment, UUID idCondo) {}