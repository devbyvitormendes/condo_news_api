package br.com.gravitech.condonews.dto;

import java.util.UUID;

public record NewsDto (UUID id, String title, String content, String image, String date, String updatedAt, boolean breaking, UUID idCondo) {}