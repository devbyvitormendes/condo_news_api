package br.com.gravitech.condonews.controller.dashboard;

import br.com.gravitech.condonews.dto.DashboardDto;
import br.com.gravitech.condonews.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/dashboard", produces = MediaType.APPLICATION_JSON_VALUE)
public class DashboardController implements DashboardApi {
    
    public final DashboardService dashboardService;
    
    @Override
    public DashboardDto getDashboardData(@PathVariable UUID id) {
        return dashboardService.getDashboardData(id);
    }

    
}

