package br.com.gravitech.condonews.dto.page;

import java.io.Serializable;
import java.util.List;

public record PageResponseDto(
    List<?> content,
    int number,
    int size,
    long totalElements,
    int totalPages
) implements Serializable {}
