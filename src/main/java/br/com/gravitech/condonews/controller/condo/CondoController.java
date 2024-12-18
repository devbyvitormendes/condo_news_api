package br.com.gravitech.condonews.controller.condo;

import br.com.gravitech.condonews.dto.CondoDto;
import br.com.gravitech.condonews.service.CondoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/v1/condo")
public class CondoController implements CondoApi {

    private final CondoService condoService;

    @Override
    @GetMapping
    public List<CondoDto> getCondo() {
        return condoService.findAllCondos();
    }

    @Override
    @GetMapping("/{id}")
    public CondoDto getCondo(@PathVariable UUID id) {
        return condoService.findCondoById(id);
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createCondo(@RequestBody CondoDto condo) {
        condoService.createCondo(condo);
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

