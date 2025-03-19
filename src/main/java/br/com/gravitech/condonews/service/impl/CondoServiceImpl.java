package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.Condo;
import br.com.gravitech.condonews.domain.utils.StringConstants;
import br.com.gravitech.condonews.dto.CondoDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import br.com.gravitech.condonews.exception.condo.CondoNotFoundException;
import br.com.gravitech.condonews.mapper.CondoMapper;
import br.com.gravitech.condonews.mapper.PageMapper;
import br.com.gravitech.condonews.repository.CondoRepository;
import br.com.gravitech.condonews.service.CondoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CondoServiceImpl implements CondoService {

    private final CondoMapper condoMapper;
    private final PageMapper pageMapper;
    private final CondoRepository condoRepository;

    @Override
    public PageResponseDto findAllCondos(Pageable pageable) {
        log.info(StringConstants.Log.STARTING_METHOD, "findAllCondos");
        var result = pageMapper.toResponseDto(condoRepository.findAll(pageable));
        log.info(StringConstants.Log.ENDING_METHOD, "findAllCondos");
        return result;
    }

    @Override
    public CondoDto findCondoById(UUID id) {
        log.info(StringConstants.Log.Condo.FINDING_CONDO, id);
        var result = condoMapper.toDto(condoRepository.findById(id).orElseThrow(CondoNotFoundException::new));
        log.info(StringConstants.Log.ENDING_METHOD, "findCondoById");
        return result;
    }

    @Override
    @Transactional
    public CondoDto createCondo(CondoDto condo) {
        log.info(StringConstants.Log.Condo.CREATING_CONDO, condo.name());
        Condo savedCondo = condoRepository.save(condoMapper.toEntity(condo));
        var result = condoMapper.toDto(savedCondo);
        log.info(StringConstants.Log.ENDING_METHOD, "createCondo");
        return result;
    }

    @Override
    @Transactional
    public CondoDto updateCondo(CondoDto condo) {
        log.info(StringConstants.Log.Condo.UPDATING_CONDO, condo.id());
        Condo entity = condoRepository.findById(condo.id()).orElseThrow(CondoNotFoundException::new);
        condoMapper.merge(condo, entity);
        var result = condoMapper.toDto(condoRepository.save(entity));
        log.info(StringConstants.Log.ENDING_METHOD, "updateCondo");
        return result;
    }

    @Override
    @Transactional
    public void deleteCondo(UUID id) {
        log.info(StringConstants.Log.Condo.DELETING_CONDO, id);
        CondoDto condo = findCondoById(id);
        condoRepository.deleteById(condo.id());
        log.info(StringConstants.Log.ENDING_METHOD, "deleteCondo");
    }
}
