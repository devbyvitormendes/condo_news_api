package br.com.gravitech.condonews.controller.resident;

import br.com.gravitech.condonews.dto.ResidentDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Resident", description = "Resident related operations")
public interface ResidentApi {

    @Operation(summary = "Get Residents", description = "Get a list of all Residents.")
    PageResponseDto getResidents(Pageable pageable);

    @Operation(summary = "Get Resident by ID", description = "Get a Resident by UUID.")
    ResidentDto getResident(@Parameter(description = "Resident's UUID", required = true) @PathVariable UUID id);

    @Operation(summary = "Create Resident", description = "Create a new Resident in database.")
    ResidentDto createResident(@RequestBody ResidentDto resident);

    @Operation(summary = "Update Resident", description = "Update Resident data in database.")
    ResidentDto updateResident(@Parameter(description = "Resident data", required = true) @RequestBody ResidentDto resident);

    @Operation(summary = "Delete Resident", description = "Delete a Resident in database.")
    void deleteResident(@Parameter(description = "Resident's UUID", required = true) @PathVariable UUID id);
}
