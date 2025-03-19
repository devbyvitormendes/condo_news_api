package br.com.gravitech.condonews.dto;

import java.util.List;

public record DashboardDto(
    List<DashboardItemDto> items
) {}
