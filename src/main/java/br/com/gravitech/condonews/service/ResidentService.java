package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.ResidentDto;

import java.util.List;
import java.util.UUID;

public interface ResidentService {
    List<ResidentDto> findAllResidents();

    ResidentDto findResidentById(UUID id);

    ResidentDto createResident(ResidentDto resident);

    ResidentDto updateResident(ResidentDto resident);

    void deleteResident(UUID id);
}
