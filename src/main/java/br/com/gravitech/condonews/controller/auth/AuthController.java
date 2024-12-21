package br.com.gravitech.condonews.controller.auth;

import br.com.gravitech.condonews.config.util.JwtUtil;
import br.com.gravitech.condonews.domain.auth.AuthenticationRequest;
import br.com.gravitech.condonews.domain.auth.AuthenticationResponse;
import br.com.gravitech.condonews.dto.auth.AuthRequestDto;
import br.com.gravitech.condonews.dto.auth.AuthResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping(value = "/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthController implements AuthApi {

    private final JwtUtil jwtUtil;
    private final UserDetailsService userDetailsService;

    @Override
    @PostMapping(value = "/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public AuthResponseDto createToken(@RequestBody AuthRequestDto request) {
        userDetailsService.loadUserByUsername(request.username());
        String jwtToken = jwtUtil.generateToken(request.username());

        return new AuthResponseDto(jwtToken, "");
    }
}

