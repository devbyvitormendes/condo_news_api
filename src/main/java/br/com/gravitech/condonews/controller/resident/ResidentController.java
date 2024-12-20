package br.com.gravitech.condonews.controller.resident;

import br.com.gravitech.condonews.dto.ResidentDto;
import br.com.gravitech.condonews.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/resident", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResidentController implements ResidentApi {

    private final ResidentService residentService;

    @Override
    @GetMapping
    public List<ResidentDto> getResident() {
        return residentService.findAllResidents();
    }

    @Override
    @GetMapping("/{id}")
    public ResidentDto getResident(@PathVariable UUID id) {
        return residentService.findResidentById(id);
    }

    @Override
    @PostMapping
    public ResidentDto createResident(@RequestBody ResidentDto resident) {
        return residentService.createResident(resident);
    }

    @Override
    @PutMapping
    public ResidentDto updateResident(@RequestBody ResidentDto resident) {
        return residentService.updateResident(resident);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteResident(UUID id) {
        residentService.deleteResident(id);
    }
}

