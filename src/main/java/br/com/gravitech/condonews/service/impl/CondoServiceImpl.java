package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.domain.Condo;
import br.com.gravitech.condonews.dto.CondoDto;
import br.com.gravitech.condonews.exception.condo.CondoNotFoundException;
import br.com.gravitech.condonews.mapper.CondoMapper;
import br.com.gravitech.condonews.repository.CondoRepository;
import br.com.gravitech.condonews.service.CondoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CondoServiceImpl implements CondoService {

    private final CondoMapper condoMapper;
    private final CondoRepository condoRepository;

    @Override
    public List<CondoDto> findAllCondos() {
        return condoMapper.toDtoList(condoRepository.findAll());
    }

    @Override
    public CondoDto findCondoById(UUID id) {
        return condoMapper.toDto(condoRepository.findById(id).orElseThrow(CondoNotFoundException::new));
    }

    @Override
    @Transactional
    public CondoDto createCondo(CondoDto condo) {
        Condo savedCondo = condoRepository.save(condoMapper.toEntity(condo));
        return condoMapper.toDto(savedCondo);
    }

    @Override
    @Transactional
    public CondoDto updateCondo(CondoDto condo) {
        Condo entity = condoRepository.findById(condo.getId()).orElseThrow(CondoNotFoundException::new);
        condoMapper.merge(condo, entity);
        return null;
    }

    @Override
    @Transactional
    public void deleteCondo(UUID id) {
        CondoDto condo = findCondoById(id);
        condoRepository.deleteById(condo.getId());
    }
}
