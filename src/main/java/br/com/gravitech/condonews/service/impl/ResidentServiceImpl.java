package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.Resident;
import br.com.gravitech.condonews.dto.ResidentDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import br.com.gravitech.condonews.exception.resident.ResidentNotFoundException;
import br.com.gravitech.condonews.mapper.PageMapper;
import br.com.gravitech.condonews.mapper.ResidentMapper;
import br.com.gravitech.condonews.repository.ResidentRepository;
import br.com.gravitech.condonews.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentMapper residentMapper;
    private final PageMapper pageMapper;
    private final ResidentRepository residentRepository;

    @Override
    public PageResponseDto findAllResidents(Pageable pageable) {
        return pageMapper.toResponseDto(residentRepository.findAll(pageable));
    }

    @Override
    public ResidentDto findResidentById(UUID id) {
        return residentMapper.toDto(residentRepository.findById(id).orElseThrow(ResidentNotFoundException::new));
    }

    @Override
    public ResidentDto createResident(ResidentDto resident) {
        Resident savedResident = residentRepository.save(residentMapper.toEntity(resident));
        return residentMapper.toDto(savedResident);
    }

    @Override
    public ResidentDto updateResident(ResidentDto resident) {
        Resident entity = residentRepository.findById(resident.getId()).orElseThrow(ResidentNotFoundException::new);
        residentMapper.merge(resident, entity);
        return residentMapper.toDto(residentRepository.save(entity));
    }

    @Override
    public void deleteResident(UUID id) {
        ResidentDto resident = findResidentById(id);
        residentRepository.deleteById(resident.getId());
    }
}
