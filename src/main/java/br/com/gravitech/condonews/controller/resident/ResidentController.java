package br.com.gravitech.condonews.controller.resident;

import br.com.gravitech.condonews.dto.ResidentDto;
import br.com.gravitech.condonews.service.ResidentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/resident")
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
    public void createResident(@RequestBody ResidentDto resident) {
        residentService.createResident(resident);
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

