package br.com.gravitech.condonews.dto;

import java.util.UUID;

public record ContactDto(UUID id, String name, String email, String phone, String type) {
}
