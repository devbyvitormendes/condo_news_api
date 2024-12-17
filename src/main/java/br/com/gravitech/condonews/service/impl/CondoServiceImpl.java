package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.dto.CondoDto;
import br.com.gravitech.condonews.mapper.CondoMapper;
import br.com.gravitech.condonews.repository.CondoRepository;
import br.com.gravitech.condonews.service.CondoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void createCondo(CondoDto condo) {
        condoRepository.save(condoMapper.toEntity(condo));
    }
}
