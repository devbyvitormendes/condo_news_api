package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.Resident;
import br.com.gravitech.condonews.dto.ResidentDto;
import br.com.gravitech.condonews.exception.resident.ResidentNotFoundException;
import br.com.gravitech.condonews.mapper.ResidentMapper;
import br.com.gravitech.condonews.repository.ResidentRepository;
import br.com.gravitech.condonews.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentMapper residentMapper;
    private final ResidentRepository residentRepository;

    @Override
    public List<ResidentDto> findAllResidents() {
        return residentMapper.toDtoList(residentRepository.findAll());
    }

    @Override
    public ResidentDto findResidentById(UUID id) {
        return residentMapper.toDto(residentRepository.findById(id).orElseThrow(ResidentNotFoundException::new));
    }

    @Override
    public void createResident(ResidentDto resident) {
        residentRepository.save(residentMapper.toEntity(resident));
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
