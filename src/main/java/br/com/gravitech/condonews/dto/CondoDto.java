package br.com.gravitech.condonews.dto;

import java.util.List;
import java.util.UUID;

public record CondoDto (UUID id, String name, List<ResidentDto> residents, List<NewsDto> news) {}
