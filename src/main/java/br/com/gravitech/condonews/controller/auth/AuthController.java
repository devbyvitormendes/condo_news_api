package br.com.gravitech.condonews.controller.auth;

import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import br.com.gravitech.condonews.dto.auth.RefreshRequestDto;
import br.com.gravitech.condonews.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponseDto createToken(@RequestBody AuthRequestDto request) {
        return authService.authenticate(request);
    }

    @Override
    @PostMapping(value = "/refresh-token", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponseDto refreshToken(RefreshRequestDto request) {
        return null;
    }
}

