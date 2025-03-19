package br.com.gravitech.condonews.service;

import java.util.UUID;

import br.com.gravitech.condonews.dto.DashboardDto;

public interface DashboardService {

    DashboardDto getDashboardData(UUID id);
}
