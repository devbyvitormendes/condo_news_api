package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.Condo;
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
        log.info("Starting findAllCondos method {}", pageable);
        return pageMapper.toResponseDto(condoRepository.findAll(pageable));
    }

    @Override
    public CondoDto findCondoById(UUID id) {
        log.info("Starting findCondoById method {}", id);
        return condoMapper.toDto(condoRepository.findById(id).orElseThrow(CondoNotFoundException::new));
    }

    @Override
    @Transactional
    public CondoDto createCondo(CondoDto condo) {
        log.info("Starting createCondo method {}", condo);
        Condo savedCondo = condoRepository.save(condoMapper.toEntity(condo));
        return condoMapper.toDto(savedCondo);
    }

    @Override
    @Transactional
    public CondoDto updateCondo(CondoDto condo) {
        log.info("Starting updateCondo method {}", condo);
        Condo entity = condoRepository.findById(condo.id()).orElseThrow(CondoNotFoundException::new);
        condoMapper.merge(condo, entity);
        return null;
    }

    @Override
    @Transactional
    public void deleteCondo(UUID id) {
        log.info("Starting deleteCondo method {}", id);
        CondoDto condo = findCondoById(id);
        condoRepository.deleteById(condo.id());
    }
}
