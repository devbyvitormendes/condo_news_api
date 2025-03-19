package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.Resident;
import br.com.gravitech.condonews.domain.utils.StringConstants;
import br.com.gravitech.condonews.dto.ResidentDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import br.com.gravitech.condonews.exception.resident.ResidentNotFoundException;
import br.com.gravitech.condonews.mapper.PageMapper;
import br.com.gravitech.condonews.mapper.ResidentMapper;
import br.com.gravitech.condonews.repository.ResidentRepository;
import br.com.gravitech.condonews.service.ResidentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentMapper residentMapper;
    private final PageMapper pageMapper;
    private final ResidentRepository residentRepository;

    @Override
    public PageResponseDto findAllResidents(Pageable pageable) {
        log.info(StringConstants.Log.STARTING_METHOD, "findAllResidents");
        var result = pageMapper.toResponseDto(residentRepository.findAll(pageable));
        log.info(StringConstants.Log.ENDING_METHOD, "findAllResidents");
        return result;
    }

    @Override
    public ResidentDto findResidentById(UUID id) {
        log.info(StringConstants.Log.Resident.FINDING_RESIDENT, id);
        var result = residentMapper.toDto(residentRepository.findById(id).orElseThrow(ResidentNotFoundException::new));
        log.info(StringConstants.Log.ENDING_METHOD, "findResidentById");
        return result;
    }

    @Override
    public ResidentDto createResident(ResidentDto resident) {
        log.info(StringConstants.Log.Resident.CREATING_RESIDENT, resident.name());
        Resident savedResident = residentRepository.save(residentMapper.toEntity(resident));
        var result = residentMapper.toDto(savedResident);
        log.info(StringConstants.Log.ENDING_METHOD, "createResident");
        return result;
    }

    @Override
    public ResidentDto updateResident(ResidentDto resident) {
        log.info(StringConstants.Log.Resident.UPDATING_RESIDENT, resident.id());
        Resident entity = residentRepository.findById(resident.id()).orElseThrow(ResidentNotFoundException::new);
        residentMapper.merge(resident, entity);
        var result = residentMapper.toDto(residentRepository.save(entity));
        log.info(StringConstants.Log.ENDING_METHOD, "updateResident");
        return result;
    }

    @Override
    public void deleteResident(UUID id) {
        log.info(StringConstants.Log.Resident.DELETING_RESIDENT, id);
        ResidentDto resident = findResidentById(id);
        residentRepository.deleteById(resident.id());
        log.info(StringConstants.Log.ENDING_METHOD, "deleteResident");
    }
}
