package br.com.gravitech.condonews.controller.condo;

import br.com.gravitech.condonews.dto.CondoDto;
import br.com.gravitech.condonews.dto.page.PageResponseDto;
import br.com.gravitech.condonews.service.CondoService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/v1/condo", produces = MediaType.APPLICATION_JSON_VALUE)
public class CondoController implements CondoApi {

    private final CondoService condoService;

    @Override
    @GetMapping
    public PageResponseDto getCondos(Pageable pageable) {
        return condoService.findAllCondos(pageable);
    }

    @Override
    @GetMapping("/{id}")
    public CondoDto getCondo(@PathVariable UUID id) {
        return condoService.findCondoById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CondoDto createCondo(@RequestBody CondoDto condo) {
        return condoService.createCondo(condo);
    }

    @Override
    @PutMapping
    public CondoDto updateCondo(@RequestBody CondoDto condo) {
        return condoService.updateCondo(condo);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteCondo(@PathVariable UUID id) {
        condoService.deleteCondo(id);
    }
}

