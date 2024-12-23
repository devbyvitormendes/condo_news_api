package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.CondoDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface CondoService {
    PageResponseDto findAllCondos(Pageable pageable);

    CondoDto findCondoById(UUID id);

    CondoDto createCondo(CondoDto condo);

    CondoDto updateCondo(CondoDto condo);

    void deleteCondo(UUID id);
}
