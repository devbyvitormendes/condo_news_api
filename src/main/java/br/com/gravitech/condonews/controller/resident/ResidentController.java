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
        return List.of();
    }

    @Override
    @GetMapping("/{id}")
    public ResidentDto getResident(@PathVariable UUID id) {
        return null;
    }

    @Override
    @PostMapping
    public void createResident(@RequestBody ResidentDto resident) {

    }

    @Override
    @PutMapping
    public ResidentDto updateResident(@RequestBody ResidentDto resident) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteResident(UUID id) {

    }
}

