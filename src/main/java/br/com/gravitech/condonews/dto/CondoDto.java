package br.com.gravitech.condonews.dto;

import java.util.UUID;

public record CondoDto (UUID id, String name, String address, String city, String state, String zipCode) {}
