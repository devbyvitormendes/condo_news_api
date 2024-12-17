package br.com.gravitech.condonews.service.impl;

import br.com.gravitech.condonews.repository.ResidentRepository;
import br.com.gravitech.condonews.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ResidentServiceImpl implements ResidentService {

    private final ResidentRepository residentRepository;

}
