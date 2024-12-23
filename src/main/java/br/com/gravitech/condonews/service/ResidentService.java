package br.com.gravitech.condonews.service;

import br.com.gravitech.condonews.dto.ResidentDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import org.springframework.data.domain.Pageable;

import java.util.UUID;

public interface ResidentService {
    PageResponseDto findAllResidents(Pageable pageable);

    ResidentDto findResidentById(UUID id);

    ResidentDto createResident(ResidentDto resident);

    ResidentDto updateResident(ResidentDto resident);

    void deleteResident(UUID id);
}
