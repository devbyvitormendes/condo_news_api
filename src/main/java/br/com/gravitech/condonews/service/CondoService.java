package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.CondoDto;

import java.util.List;

public interface CondoService {
    List<CondoDto> findAllCondos();
    void createCondo(CondoDto condo);
}
