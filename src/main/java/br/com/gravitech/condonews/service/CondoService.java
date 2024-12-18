package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.CondoDto;

import java.util.List;
import java.util.UUID;

public interface CondoService {
    List<CondoDto> findAllCondos();

    CondoDto findCondoById(UUID id);

    void createCondo(CondoDto condo);

    CondoDto updateCondo(CondoDto condo);

    void deleteCondo(UUID id);
}
