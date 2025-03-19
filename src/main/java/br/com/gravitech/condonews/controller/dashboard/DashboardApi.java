package br.com.gravitech.condonews.controller.dashboard;

import br.com.gravitech.condonews.dto.DashboardDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@Tag(name = "Dashboard", description = "Dashboard related operations")
public interface DashboardApi {

    @Operation(summary = "Get Dashborad by Condo ID", description = "Get dashboard data from a condo by UUID.")
    DashboardDto getDashboardData(@Parameter(description = "Condo's UUID", required = true) @PathVariable UUID id);

}
