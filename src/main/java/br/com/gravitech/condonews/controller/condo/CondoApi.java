package br.com.gravitech.condonews.controller.condo;

import br.com.gravitech.condonews.dto.CondoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.UUID;

@Tag(name = "Condo", description = "Condo related operations")
public interface CondoApi {

    @Operation(summary = "Get Condos", description = "Get a list of all Condos.")
    List<CondoDto> getCondo();

    @Operation(summary = "Get Condo by ID", description = "Get a Condo by UUID.")
    CondoDto getCondo(@Parameter(description = "Condo's UUID", required = true) @PathVariable UUID id);

    @Operation(summary = "Create Condo", description = "Create a new Condo in database.")
    void createCondo(@RequestBody CondoDto condo);

    @Operation(summary = "Update Condo", description = "Update Condo data in database.")
    CondoDto updateCondo(@Parameter(description = "Condo data", required = true) @RequestBody CondoDto condo);

    @Operation(summary = "Delete Condo", description = "Delete a Condo in database.")
    void deleteCondo(@Parameter(description = "Condo's UUID", required = true) @PathVariable UUID id);
}
